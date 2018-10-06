//reads SCAF data files, converts from SH4 format to XML to be used
//by SubSkipper

package fps.subskipper.scafparser;

import fps.subskipper.core.Ship;
import fps.subskipper.core.Ships;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;

import static fps.subskipper.util.Constants.SCAF_ROOT_PATH;

public class ScafParser implements IScafParser {

    final static Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void writeShipsToFile(Ships ships) throws JAXBException {
        EntityXmlMarshaller shipMarshaller = new EntityXmlMarshaller();
        try {
            shipMarshaller.writeShipsToXml(ships);
        }
        catch(JAXBException j){
            logger.error("Threw JAXB exception when writing ships to file.", j.getMessage());
            throw j;
        }
    }

    @Override
    public Ships loadShipsToMemory() throws JAXBException, IOException {
            EntityXmlMarshaller readShips = new EntityXmlMarshaller();
            Ships ships;
            try {
                    ships = readShips.readShipsFromXml();
                }
                catch (JAXBException j) {
                    logger.error("Threw JAXB exception when reading ships from file.", j.getMessage());
                    throw j;
                }
                if(ships != null && !ships.getShips().isEmpty()){
                    return ships;
                }
                else{
                    try {
                        ships = parseShipsFromScaf(SCAF_ROOT_PATH);
                    }
                    catch (IOException e) {
                        logger.error("Threw IOException when parsing ships from SCAF.", e.getMessage());
                        throw e;
                    }
                }
                return ships;
            }


    public Ships parseShipsFromScaf(String scafPath) throws IOException {
        logger.info("ScafParser.parseShipsFromScaf() started.");
        ArrayList<File> shipFiles = new ArrayList<>();
        ArrayList<Ship> shipData = new ArrayList<>();

        listShipFiles(scafPath, shipFiles);

        for (int i = 0; i < shipFiles.size(); i++) {
            shipData.add(makeShip(shipFiles.get(i).toString()));
        }
        return new Ships(shipData);
    }

    //Format and construct a ship object using data in tempShips
    public Ship makeShip(String path) throws IOException {
        //First run listF
        ArrayList<File> shipFiles = new ArrayList<File>();
        listShipFiles("data", shipFiles);
        String[] tempShips = formatScafShipRecord(path);
        stripVars(tempShips); //remove descriptor strings.

        String name = nameLookup(tempShips[0]);
        int type = Integer.parseInt(tempShips[1]);
        String typeName = typeNameLookup(tempShips[1]);

        String imagePath = path.substring(0, path.length() - 4);
        //this cuts off the last 4 letters in the string. Usually, this would be .cfg, but it might not be.
        //TODO: implement a better solution for finding the imagePath.
        imagePath += "_sil.dds";
        imagePath = "\"" + imagePath + "\"";

        double maxSpeed = Double.parseDouble(tempShips[2]);
        double length = Double.parseDouble(tempShips[3]);
        double width = Double.parseDouble(tempShips[4]);
        double mast = Double.parseDouble(tempShips[5]);
        double draft = Double.parseDouble(tempShips[6]);
        double disp = Double.parseDouble(tempShips[7]);

        Ship testShip = new Ship(name, type, typeName, imagePath, maxSpeed, length, width, mast, draft, disp);
        return testShip;
    }

    //recursively goes through directories, filters out ship cfg files. TODO: should be listShipFiles or to that effect
    private void listShipFiles(String directoryName, ArrayList<File> files) {
        File directory = new File(directoryName);
        // recursively list files in directory and sub directories.
        File[] fList = directory.listFiles();
        for (File file : fList) {
            if (file.isFile() &&
                    //Exclude these as for some reason their data is broken:
                    !((file.toString().toLowerCase().contains("walleye")) ||
                            (file.toString().toLowerCase().contains("nde_parker")) ||
                            (file.toString().toLowerCase().contains("ryuun")) ||
                            (file.toString().toLowerCase().contains("cargodef")) ||
                            (file.toString().toLowerCase().contains("roster")))
                    //file extension filter. We're only interested in .cfg
                    && file.toString().toLowerCase().endsWith(".cfg")
                    ) {
                files.add(file);
            }
            else if (file.isDirectory()) {
                listShipFiles(file.getPath(), files);
            }
        }
    }

    //This method formats a SCAF record for parsing with parseShipsFromScaf()
    private String[] formatScafShipRecord(String file) throws IOException {
        String[] tempShips = new String[8];
        FileInputStream fs = null;
        BufferedReader br = null;
        try {
            fs = new FileInputStream(file);
            br = new BufferedReader(new InputStreamReader(fs));
            String curLine = "";
            int append = 0; // incremented when adding to tempShips array so we don't add nulls.
            while (br.ready()) {
                curLine = br.readLine().trim();
                //SCAF files use semiColon for comments. This breaks everything.
                if (curLine.contains(";")) {
                    curLine = curLine.substring(0, curLine.indexOf(";") - 1);
                }
                if (curLine.contains("[Unit]")) {//checks if the record is valid
                    curLine = br.readLine().trim(); //move to next line after check
                }
                //Skip this line as it is unused.
                if (curLine.contains("3DModel")) {
                    curLine = br.readLine().trim();
                }
                //We extracted all the useful records.
                if (curLine.contains("Renown") || curLine.contains("DisplacementVariation")) {
                    br.close();
                    break;
                }
                //start saving to temp array of lines. Each array creates one ship
                //Skip any blank lines.
                else if (!(curLine.equals(null) || curLine.equals("") || curLine == null)) {
                    tempShips[append] = curLine;
                    append++;
                }
            }
        }
        catch (FileNotFoundException f) {
            logger.info("File not found: ", file);
        }
        catch (IOException e) {
            logger.info("could not read file.", e.getMessage());
            throw e;
        }
        finally {
            br.close();
        }
        return tempShips;
    }

    //methods for further formatting public array tempShips into format suitable for Ship.class
    //after that construct an instance of the ship, to be later parsed to XML. takes no arguments
    public void stripVars(String[] tempShips) { //Strips incompatible data from array created by loadShipsToMemory
        String curTempShip = "";
        for (int i = 0; i < tempShips.length; i++) { //go through all array cells.

            if (tempShips[i] == null) {
                break;
            } else {
                curTempShip = tempShips[i];
                curTempShip = curTempShip.substring(curTempShip.indexOf("=") + 1, curTempShip.length());
                tempShips[i] = curTempShip;
            }
        }
    }

    //this method looks up a query from names.cfg using a linear line-by-line search.
    //takes the short className from the Ship file as its input, and returns a stripped ship name.
    private String typeNameLookup(String typeNum) throws IOException {
        typeNum = "Type" + typeNum.trim();
        return nameLookup(typeNum);
    }

    //Looks up a name using a String Query from the separate Names.cfg file
    private String nameLookup(String query) throws IOException {
        boolean found = false;
        String curLine = "ReadShips.nameLookup() failed";
        FileInputStream fs = null;

        try {
            fs = new FileInputStream(""); //TODO: was: fs = new FileInputStream(namesPath);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(fs))) {
                while (!found) {
                    if (!br.ready()) {
                        logger.info("Reached Names.cfg EOF. Breaking.");
                        break;
                    }
                    curLine = br.readLine().trim();

                    if (curLine.contains(query)) { //we found the name, change tempShips[0]
                        curLine = curLine.substring(curLine.indexOf("=") + 1, curLine.length());
                        br.close();
                        found = true;
                    }
                }
            }
        } catch (FileNotFoundException f) {
            logger.info("Could not find file.");

        } catch (IOException e) {
            e.printStackTrace();
            logger.info("could not read file.");
        }
        finally {
            fs.close();
        }
        if (found) {
            return curLine;
        }
        else {
            return curLine + " | ERROR: \"" + query + "\" not found in ReadShips.nameLookup().";
        }
    }
}


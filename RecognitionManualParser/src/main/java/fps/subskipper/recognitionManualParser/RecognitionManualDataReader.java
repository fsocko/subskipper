//reads SCAF data files, converts from SH4 format to XML to be used
//by SubSkipper

package fps.subskipper.recognitionManualParser;

import fps.subskipper.core.Ship;
import fps.subskipper.core.Ships;
import fps.subskipper.recognitionManualParser.util.ConstantsRecog;
import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.ArrayList;

import static fps.subskipper.recognitionManualParser.util.ConstantsRecog.*;

@Deprecated
@Slf4j
public class RecognitionManualDataReader {

    public void writeShipsToFile(Ships ships) throws JAXBException {
        EntityXmlMarshaller shipMarshaller = new EntityXmlMarshaller();
        try {
            shipMarshaller.writeShipsToXml(ships);
        } catch (JAXBException j) {
            log.error("Threw JAXB exception when writing ships to file.", j);
            throw j;
        }
    }

    public Ships loadShipsToMemory() throws IOException {
        EntityXmlMarshaller readShips = new EntityXmlMarshaller();
        Ships ships;
        try {
            ships = readShips.readShipsFromXml();
        } catch (Exception e) {
            log.error("Threw exception when reading ships from file.", e);
        }
        try {
            ships = parseShipsFromScaf();
        } catch (IOException e) {
            log.error("Threw IOException when parsing ships from SCAF.", e);
            throw e;
        }
        return ships;
    }


    public Ships parseShipsFromScaf() throws IOException {
        log.info("RecognitionManualDataReader.parseShipsFromScaf() started.");
        ArrayList<File> shipFiles = new ArrayList<>();
        ArrayList<Ship> shipData = new ArrayList<>();

        listShipFiles(SCAF_ROOT_PATH, shipFiles);

        for (int i = 0; i < shipFiles.size(); i++) {
            shipData.add(makeShip(shipFiles.get(i).toString()));
        }
        return new Ships(shipData);
    }

    //Format and construct a ship object using data in tempShips
    public Ship makeShip(String path) throws IOException {
        ArrayList<File> shipFiles = new ArrayList<File>();
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

        Ship testShip = new Ship(name, type, typeName, imagePath, "IMAGE", maxSpeed, length, width, mast, draft, disp);
        return testShip;
    }

    //recursively goes through directories, filters out ship cfg files. TODO: should be listShipFiles or to that effect
    public void listShipFiles(String directoryName, ArrayList<File> files) {
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
            } else if (file.isDirectory()) {
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
        } catch (FileNotFoundException f) {
            log.info("File not found: ", file);
        } catch (IOException e) {
            log.info("could not read file.", e.getMessage());
            throw e;
        } finally {
            br.close();
        }
        return tempShips;
    }

    //methods for further formatting public array tempShips into format suitable for Ship.class
    //after that construct an instance of the ship, to be later parsed to XML.
    private void stripVars(String[] tempShips) { //Strips incompatible data from array created by loadShipsToMemory
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
            fs = new FileInputStream(ConstantsRecog.SCAF_NAMES_PATH); //TODO: was: fs = new FileInputStream(namesPath);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(fs))) {
                while (!found) {
                    if (!br.ready()) {
                        log.info("Reached Names.cfg EOF. Breaking.");
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
            log.info("Could not find file.", f);

        } catch (IOException e) {
            e.printStackTrace();
            log.info("could not read file.", e);
        } finally {
            fs.close();
        }
        if (found) {
            return curLine;
        } else {
            log.error(curLine + "query not found in ReadShips.nameLookup().");
            return curLine + " | ERROR: \"" + query + "\" not found in ReadShips.nameLookup().";
        }
    }
}


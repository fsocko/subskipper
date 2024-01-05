package ship.data.reader.sh4;

import fps.subskipper.core.Ship;
import fps.subskipper.core.Ships;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import ship.data.reader.IShipDataReader;
import ship.data.reader.sh4.image.processor.IImageProcessor;
import ship.data.reader.sh4.image.processor.ImageProcessorImpl;

import java.io.*;
import java.util.ArrayList;

import static fps.subskipper.util.Constants.SCAF_NAMES_PATH;
import static fps.subskipper.util.Constants.SCAF_ROOT_PATH;

@Slf4j
@Getter
public class ReadShipsFromSh4DataImpl implements IShipDataReader {

    private ArrayList<File> shipFiles = new ArrayList<>();

    @Override
    public Ships readShipsFromData(File shipDataPath) {
        try {
            return this.parseShipsFromScaf();
        } catch (Exception e) {
            log.error("Failed to read SH4 Ships: ", e);
            throw new RuntimeException(e);
        }
    }

    public Ships loadShipsToMemory() throws IOException {

        //FIXME: Read from existing file if possible
//        Ships ships = null;
//        try {
//            ships = JsonEntityMarshaller.readShipsFromFile();
//        } catch (Exception e) {
//            log.error("Threw exception when reading SH4 ships from existing JSON file.", e);
//
//                try {
//                    ships = parseShipsFromScaf();
//                } catch (IOException ioe) {
//                    log.error("Threw IOException when parsing SH4 ships from SCAF.", ioe);
//                    throw ioe;
//                }
//        }
        return parseShipsFromScaf();
    }

    public Ships parseShipsFromScaf() throws IOException {
        log.info("parseShipsFromScaf() started.");
        this.shipFiles = new ArrayList<>();
        ArrayList<Ship> shipData = new ArrayList<>();

        listShipFiles(SCAF_ROOT_PATH);

        for (int i = 0; i < shipFiles.size(); i++) {
            shipData.add(makeShip(shipFiles.get(i).toString()));
        }
        return new Ships(shipData);
    }


    //Format and construct a ship object using data in tempShips
    public Ship makeShip(String path) throws IOException {
        String[] tempShips = formatScafShipRecord(path);
        stripVars(tempShips); //remove descriptor strings.

        String name = nameLookup(tempShips[0]);
        int type = Integer.parseInt(tempShips[1]);
        String typeName = typeNameLookup(tempShips[1]);

        String imagePath = path.substring(0, path.length() - 4);
        //this cuts off the last 4 letters in the string. Usually, this would be .cfg, but it might not be.
        //TODO: implement a better solution for finding the imagePath.
        imagePath += "_sil.dds";

        IImageProcessor sh4ImageProcessor = new ImageProcessorImpl();
        String image = sh4ImageProcessor.readDdsFileToB64Png(imagePath);

        double maxSpeed = Double.parseDouble(tempShips[2]);
        double length = Double.parseDouble(tempShips[3]);
        double width = Double.parseDouble(tempShips[4]);
        double mast = Double.parseDouble(tempShips[5]);
        double draft = Double.parseDouble(tempShips[6]);
        double displacement = Double.parseDouble(tempShips[7]);

        Ship testShip = new Ship(name, type, typeName, image, maxSpeed, length, width, mast, draft, displacement);
        return testShip;
    }

    //recursively goes through directories, filters out ship cfg files.
    // TODO: should be listShipFiles or to that effect and return a Ships object
    //TODO: was private
    protected void listShipFiles(String directoryName) {
        File directory = new File(directoryName);
        // recursively list files in directory and subdirectories.
        File[] fList = directory.listFiles();

        if(fList.length == 0){
            log.error("No Files found for SH4 SCAF.");
            //TODO: throw exception;
        }

        for (File file : fList) {
            if (file.isFile() &&
                    //file extension filter. We're only interested in .cfg
                    file.toString().toLowerCase().endsWith(".cfg") &&
                    //Exclude these as for some reason their data is broken:
                    !((file.toString().toLowerCase().contains("walleye")) ||
                            (file.toString().toLowerCase().contains("nde_parker")) ||
                            (file.toString().toLowerCase().contains("ryuun")) ||
                            (file.toString().toLowerCase().contains("cargodef")) ||
                            (file.toString().toLowerCase().contains("roster")))
            ) {
                this.shipFiles.add(file);
            } else if (file.isDirectory()) {
                listShipFiles(file.getPath());
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
            fs.close();
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

    //FIXME: This should probably be saved in a temp file as a hash
    //this method looks up a query from names.cfg using a linear line-by-line search.
    //takes the short className from the Ship file as its input, and returns a stripped ship name.
    private String typeNameLookup(String typeNum) throws IOException {
        typeNum = "Type" + typeNum.trim();
        return nameLookup(typeNum);
    }

    //FIXME: This should probably be saved in a temp file as a hash
    //Looks up a name using a String Query from the separate Names.cfg file
    private String nameLookup(String query) throws IOException {
        boolean found = false;
        String curLine = "ReadShips.nameLookup() failed";
        FileInputStream fs = null;

        try {
            fs = new FileInputStream(SCAF_NAMES_PATH);
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

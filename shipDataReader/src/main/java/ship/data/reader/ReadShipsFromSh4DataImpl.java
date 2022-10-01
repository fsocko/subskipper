package ship.data.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import fps.subskipper.core.Ship;
import fps.subskipper.core.Ships;
import image.processor.ImageProcessor;
import image.processor.ImageProcessorImpl;
import lombok.extern.slf4j.Slf4j;

import static fps.subskipper.core.util.Constants.SCAF_NAMES_PATH;
import static fps.subskipper.core.util.Constants.SCAF_ROOT_PATH;

@Slf4j
public class ReadShipsFromSh4DataImpl {

    public Ships parseShipsFromScaf() throws IOException {
        log.info("parseShipsFromScaf() started.");
        ArrayList<File> shipFiles = new ArrayList<>();
        ArrayList<Ship> shipData = new ArrayList<>();
        listShipFiles(SCAF_ROOT_PATH, shipFiles);

        for (File textShip : shipFiles) {
            shipData.add(makeShip(textShip.toString()));
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

        ImageProcessor ip = new ImageProcessorImpl();
        String shipImageB64 = ip.ddsPathToB64Image(imagePath);

        double maxSpeed = Double.parseDouble(tempShips[2]);
        double length = Double.parseDouble(tempShips[3]);
        double width = Double.parseDouble(tempShips[4]);
        double mast = Double.parseDouble(tempShips[5]);
        double draft = Double.parseDouble(tempShips[6]);
        double disp = Double.parseDouble(tempShips[7]);

        Ship testShip = new Ship(name, type, typeName, imagePath, shipImageB64, maxSpeed, length, width, mast, draft, disp);
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

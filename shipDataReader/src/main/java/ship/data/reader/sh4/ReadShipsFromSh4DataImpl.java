package ship.data.reader.sh4;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import fps.subskipper.core.Ship;
import fps.subskipper.core.Ships;
import jakarta.xml.bind.JAXBException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import ship.data.reader.RMShip;
import ship.data.reader.sh4.image.processor.ImageProcessor;
import ship.data.reader.sh4.image.processor.ImageProcessorImpl;
import ship.data.writer.JsonEntityMarshaller;

import static fps.subskipper.util.Constants.SCAF_NAMES_PATH;
import static fps.subskipper.util.Constants.SCAF_ROOT_PATH;
import static ship.data.reader.sh4.DataReaderConstants.*;

@Slf4j
@Getter
public class ReadShipsFromSh4DataImpl {

    private ArrayList<File> shipFiles = new ArrayList<>();

    public void writeShipsToFile(Ships ships) throws IOException {
            JsonEntityMarshaller.writeShipsToJsonFile(ships);
    }

    public Ships loadShipsToMemory() throws IOException {

        Ships ships;
        try {
            ships = JsonEntityMarshaller.readShipsFromFile();
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
    public RMShip makeShip(String path) throws IOException {
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

        ImageProcessor sh4ImageProcessor = new ImageProcessorImpl();
        BufferedImage bufferedImage = sh4ImageProcessor.readDdsFileToBufferedImage(imagePath);

        double maxSpeed = Double.parseDouble(tempShips[2]);
        double length = Double.parseDouble(tempShips[3]);
        double width = Double.parseDouble(tempShips[4]);
        double mast = Double.parseDouble(tempShips[5]);
        double draft = Double.parseDouble(tempShips[6]);
        double displacement = Double.parseDouble(tempShips[7]);

        RMShip testShip = new RMShip(name, type, typeName, imagePath, bufferedImage, maxSpeed, length, width, mast, draft, displacement);
        return testShip;
    }

    //recursively goes through directories, filters out ship cfg files.
    // TODO: should be listShipFiles or to that effect and return a Ships object
    //TODO: was private
    protected void listShipFiles(String directoryName) {
        File directory = new File(directoryName);
        // recursively list files in directory and sub directories.
        File[] fList = directory.listFiles();
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

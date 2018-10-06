package fps.subskipper.scafparser;

import fps.subskipper.core.Ship;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Collections;

// TODO: Auto-generated Javadoc

/**
 * The Class PrepShipData.
 */
public class PrepShipData {

    final static Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    //This method reads from SCAF, generates a ship XML file and
    //returns an arrayList of Ship objects. It also implements
    //error checks in case for any reason a Ships can't be returned.
    //If there is an error, a null is returned.


    /*//return a prepared Ships object if possible from XML. Otherwise, parseSCAF
     * and get the Ships object. Ships is a list of ships.
     *
     * @return the ships
     */
    public Ships getShips() throws IOException {

        ReadShipXML readShips = new ReadShipXML();
        Ships parsedShips = new Ships();

        if (!readShips.readXML().equals(null)) {
            parsedShips = readShips.readXML();
            logger.info("XML read successfully.");
        } else {
            parsedShips = FullDataCycle();
        }
        return parsedShips;
    }

    /**
     * Full data cycle.
     *
     * *This method is run if there is no readily available XML file.
     * It runs both SCAF to XML as well as subsequently reading the
     * resulting file
     *
     * @return the ships
     */

    private Ships FullDataCycle() throws IOException {
        WriteShipDataToXml();
        ReadShipXML readShips = new ReadShipXML();
        Ships fcShips = new Ships();
        fcShips = readShips.readXML();
        return fcShips;
    }

    /**
     * Write ship data -     //Writes SCAF to xml
     */
    private void WriteShipDataToXml(){

        //Look up and parse data from SCAF to XML file
        ReadSCAF scaf = new ReadSCAF();
        Ships runShips = new Ships();
        runShips.setShips(scaf.makeShips()); //we have a loaded Ships object
        WriteShipXML writeShips = new WriteShipXML();

        try {
            writeShips.writeXML(runShips); //written to XML
        }
        catch(Exception e){
            logger.error(e.getMessage());
        }



        logger.info("shipList.xml file parsed from SCAF.");
    }


    /**
     * Gets the ship by ID.
     *
     * @param shipID the ship ID
     * @return the ship by ID
     */
    //Look up a ship by an ID
    public Ship getShipByID(int shipID) throws IOException {
        return getShipByID(shipID, getShips());
    }

    /**
     * Gets the ship by ID.
     *
     * @param shipID    the ship ID
     * @param shipsData the ships data
     * @return the ship by ID
     */
    public Ship getShipByID(int shipID, Ships shipsData) {

        ArrayList<Ship> shipList = shipsData.getShips();
        Ship idShip = new Ship();

        for (int i = 0; i < shipList.size(); i++) {
            if (shipList.get(i).getId() == shipID) {
                idShip = shipList.get(i);
            }
        }
        return idShip;
    }

    public void printShips(Ships printShips) {
        for (Ship ship : printShips.getShips()) {
            logger.info(ship.toString());
        }
    }

    /**
     * Sort ships type.
     *
     * @param shipRecords the ship records
     * @return the ships
     */
    //Sort Ships based on Type int; ascending
    public Ships sortShipsType(Ships shipRecords) {
        ArrayList<Ship> allShips = shipRecords.getShips();
        Ships sortedList = new Ships();
        Collections.sort(allShips, Ship.sTypeComparatorDescending);
        sortedList.setShips(allShips);
        return sortedList;
    }

    /**
     * Sort ships name.
     *
     * @param shipRecords the ship records
     * @return the ships
     */
    //Sort Ships based on Type int; ascending
    public Ships sortShipsName(Ships shipRecords) {
        ArrayList<Ship> allShips = shipRecords.getShips();
        Ships sortedList = new Ships();
        Collections.sort(allShips, Ship.sNameComparatorDescending);
        sortedList.setShips(allShips);
        return sortedList;
    }
}

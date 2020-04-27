package fps.subskipper.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Collections;


//Wrapper for marshalling XML correctly. Stores Ship object as a list of Ships
@XmlRootElement(name = "ships")
public class Ships {

    final static Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    private ArrayList<Ship> ships;

    public Ships(ArrayList<Ship> ships){
        this.setShips(ships);
    }

    public Ships(){}

//    public Ships(){
//        try {
//            //XmlUnmarshaller returns a Ships object, so this is a workaround to load a list of Ship into _this_ object.
//            this.setShips(new ScafParser().loadShipsToMemory().getShips());
//        }
//        catch (JAXBException | IOException e) {
//            logger.error("Failed to get ship list from ScafParser", e.getMessage());
//        }
//    }

    @XmlElement(name = "ship")
    public void setShips(ArrayList<Ship> ships) {
        this.ships = ships;
    }

    //return this for array list of ships
    public ArrayList<Ship> getShips() {
        return ships;
    }

    public Ship getShipByID(int shipId) {
        for (Ship idShip : ships) {
            if (idShip.getId() == shipId) {
                return idShip;
            }
        }
        return null;
    }

    //Sort Ships based on Type
    public Ships sortShipsByType() {
        ArrayList sortedShips = new ArrayList(this.getShips());
        Collections.sort(this.ships, Ship.sTypeComparatorDescending);
        this.setShips(sortedShips);
        return this;
    }

    //Sort Ships based on name
    public Ships sortShipsByName() {
        ArrayList sortedShips = new ArrayList(this.getShips());
        Collections.sort(sortedShips, Ship.sNameComparatorDescending);
        this.setShips(sortedShips);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder shipStringBuilder = new StringBuilder();
        for (Ship ship : ships) {
            shipStringBuilder.append(ship.toString() + "\n\n");
        }
        return shipStringBuilder.toString();
    }
}


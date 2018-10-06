package fps.subskipper.core;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Collections;


//Wrapper for martialing XML correctly. Stores Ship object as a list of Ships
@XmlRootElement(name = "ships")
public class Ships {

    final static Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    private ArrayList<Ship> ships;

    public Ships(ArrayList<Ship> ships){
        this.setShips(ships);
    }

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

    //Sort Ships based on Type int; ascending
    public void sortShipsByType() {
        Collections.sort(this.ships, Ship.sTypeComparatorDescending);
    }

    //Sort Ships based on Type int; ascending
    public void sortShipsByName() {
        Collections.sort(this.ships, Ship.sNameComparatorDescending);
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


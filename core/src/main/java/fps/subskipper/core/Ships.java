package fps.subskipper.core;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;

@Slf4j
@Deprecated
public class Ships {

    private ArrayList<Ship> ships;

    public Ships(ArrayList<Ship> ships){
        this.setShips(ships);
    }

    public Ships(){}

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


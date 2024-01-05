package fps.subskipper.core;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Slf4j
//@Getter
//@Setter
@Deprecated
public class Ships {

    private List<Ship> ships;

    public Ships(List<Ship> ships) {
        this.setShips(ships);
    }

    public Ships() {
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }

    //return this for array list of ships
    public List<Ship> getShipList() {
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
        List sortedShips = new ArrayList(this.getShipList());
        Collections.sort(this.ships, Ship.sTypeComparatorDescending);
        this.setShips(sortedShips);
        return this;
    }

    //Sort Ships based on name
    public Ships sortShipsByName() {
        List sortedShips = new ArrayList(this.getShipList());
        Collections.sort(sortedShips, Ship.sNameComparatorDescending);
        this.setShips(sortedShips);
        return this;
    }

    @Override
    public String toString() {
        return this.getShipList().toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ships)) return false;
        Ships ships1 = (Ships) o;

        if (this.getShipList().size() == ships1.getShipList().size()) {
            for (int i = 0; i < this.getShipList().size(); i++) {
                if (this.getShipList().get(i).compareTo(ships1.getShipList().get(i)) != 0) {
                    return false;
                }
            }
        } else{
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getShipList());
    }
}


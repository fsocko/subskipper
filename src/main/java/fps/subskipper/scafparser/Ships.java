package fps.subskipper.scafparser;


import fps.subskipper.core.Ship;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;


//Wrapper for martialing XML correctly. Stores Ship object as a list of Ships
@XmlRootElement(name = "ships")
public class Ships {

    final static Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    ArrayList<Ship> ships;

    @XmlElement(name = "ship")
    public void setShips(ArrayList<Ship> ships) {
        this.ships = ships;
    }

    //return this for array list of ships
    public ArrayList<Ship> getShips() {
        return ships;
    }

    //return a single ship at position i in ships ArrList
    public Ship getShip(int i) {
        ArrayList<Ship> multi = this.getShips();
        Ship single = multi.get(i);
        return single;
    }

    @Override
    public String toString() {
        return "Ships [ships=" + ships + "]";
    }
}


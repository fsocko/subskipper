package xmlParser;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import coreLogic.TgtShip;
 

//Wrapper for martialing XML correctly. Stores Ship object as a list of Ships
@XmlRootElement(name = "ships")
public class Ships
{
	
	final static Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
    ArrayList<TgtShip> ships;
 
    @XmlElement( name = "ship" )
    public void setShips( ArrayList<TgtShip> ships)
    {
    	this.ships = ships;
    }
    
    //return this for array list of ships
	public ArrayList<TgtShip> getShips() {
		return ships;
	}
    
	//return a single ship at position i in ships ArrList
	public TgtShip getShip(int i){
		ArrayList<TgtShip> multi = this.getShips();
		TgtShip single = multi.get(i);
		return single;
	}

	@Override
	public String toString() {
		return "Ships [ships=" + ships + "]";
	}

}


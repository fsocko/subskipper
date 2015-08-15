package xmlParser;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
 

//Wrapper for martialing XML correctly. Stores Ship object as a list of Ships
@XmlRootElement(name = "ships")
public class Ships
{
    ArrayList<Ship> ships;
 
    @XmlElement( name = "ship" )
    public void setShips( ArrayList<Ship> ships)
    {
    this.ships = ships;
    }

	public ArrayList<Ship> getShips() {
		return ships;
	}
    

}


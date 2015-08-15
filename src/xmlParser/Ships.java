package xmlParser;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
 

//Wrapper for martialing XML correctly. Stores Ship object as a list of Ships
@XmlRootElement(name = "ROOOT")
@XmlAccessorType (XmlAccessType.FIELD)
public class Ships
{
    ReadSCAF SCAFarray = new ReadSCAF();
    private ArrayList<Ship> ship = SCAFarray.makeShips();
 
    public ArrayList<Ship> getShips() {
        return ship;
    }
 
}


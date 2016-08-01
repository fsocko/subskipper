package xmlParser;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import coreLogic.Ship;
import coreLogic.Torpedo;
 

//Wrapper for martialing XML correctly. Stores Ship object as a list of Ships
@XmlRootElement(name = "torpedoes")
public class Torpedoes
{
    ArrayList<Torpedo> torpedoes;
 
    @XmlElement( name = "torpedo" )
    public void setTorpedoes( ArrayList<Torpedo> torpedoes)
    {
    	this.torpedoes = torpedoes;
    }
    
    //return this for array list of ships
	public ArrayList<Torpedo> getTorpedo() {
		return torpedoes;
	}
    
	@Override
	public String toString() {
		return "Torpedoes [torpedoes=" + torpedoes + "]";
	}

	

}



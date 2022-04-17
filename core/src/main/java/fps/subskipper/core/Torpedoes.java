package fps.subskipper.core;


import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;

@Slf4j
//Wrapper for martialing XML correctly. Stores Ship object as a list of Ships
@XmlRootElement(name = "torpedoes")
public class Torpedoes {
    ArrayList<Torpedo> torpedoes;

    @XmlElement(name = "torpedo")
    public void setTorpedoes(ArrayList<Torpedo> torpedoes) {
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



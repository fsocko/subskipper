package fps.subskipper.scafparser;

import fps.subskipper.core.Ship;
import fps.subskipper.core.Torpedo;

import java.util.List;

public interface IScafParser {

    public void readScaf();

    public void writeShipXml();

    public void writeTorpedoXml();

    public List<Ship> readShipXml();

    public List<Torpedo> readTorpedoXml();

    public Ship getShipById(int id);

}

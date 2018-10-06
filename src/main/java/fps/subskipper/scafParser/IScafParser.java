package fps.subskipper.scafParser;

import java.util.List;

public interface IScafParser {

    public void readScaf();

    public void writeShipXml();

    public void writeTorpedoXml();

    public List<core.TgtShip> readShipXml();

    public List<coreLogic.Torpedo> readTorpedoXml();

    public core.TgtShip getShipById(int id);

}

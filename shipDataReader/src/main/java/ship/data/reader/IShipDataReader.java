package ship.data.reader;

import fps.subskipper.core.Ships;

import java.io.File;

public interface IShipDataReader {

    Ships readShipsFromData(File shipDataPath) throws RuntimeException;

}

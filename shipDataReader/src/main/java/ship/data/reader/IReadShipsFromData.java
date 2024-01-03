package ship.data.reader;

import fps.subskipper.core.Ships;

import java.io.File;
import java.util.List;

public interface IReadShipsFromData {

    Ships readShipsFromFile(File shipDataFile);

}

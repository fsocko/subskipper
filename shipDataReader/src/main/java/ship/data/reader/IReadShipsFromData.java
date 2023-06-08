package ship.data.reader;

import fps.subskipper.core.Ships;

import java.io.File;

public interface IReadShipsFromData {

    Ships readShipsFromFile(File uboatDataFile);

}

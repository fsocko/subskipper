package ship.data.writer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import fps.subskipper.core.Ship;
import fps.subskipper.core.Ships;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static fps.subskipper.util.Constants.SHIPLIST_PATH;


@Slf4j
public class JsonEntityMarshaller {

    public static void writeShipsToJsonFile(Ships ships) throws IOException {
        writeShipsToJsonFile(ships, new File(SHIPLIST_PATH));
    }

    public static void writeShipsToJsonFile( Ships ships, File shipListPath) throws IOException {

        try (FileWriter writer = new FileWriter(shipListPath)) {
            Gson gson = new Gson();
            String writeShips = gson.toJson(ships.getShipList());
            writer.write(writeShips);
        } catch (IOException ie) {
            log.error("Failed to write Ships.", ie);
            throw ie;
        }
    }

    public static Ships readShipsFromFile() throws IOException {
        return readShipsFromFile( new File(SHIPLIST_PATH));
    }

    public static Ships readShipsFromFile(File shipListPath) throws IOException {
        try {
            Gson gson = new Gson();
            String readJsonShips = Files.lines(Paths.get(shipListPath.getCanonicalPath()))
                    .collect(Collectors.joining("\n"));

            Type shipType = new TypeToken<ArrayList<Ship>>() {}.getType();
            List<Ship> jsonReadList = gson.fromJson(readJsonShips, shipType);
            return new Ships(jsonReadList);


        } catch (IOException ie) {
            log.error("Failed to read Ships:", ie);
            throw ie;
        }
    }

}

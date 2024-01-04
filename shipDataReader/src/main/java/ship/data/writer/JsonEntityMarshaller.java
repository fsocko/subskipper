package ship.data.writer;

import com.google.gson.Gson;
import fps.subskipper.core.Ships;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import static fps.subskipper.util.Constants.SHIPLIST_PATH;


@Slf4j
public class JsonEntityMarshaller {

    public static void writeShipsToJsonFile(Ships ships) throws IOException {
        writeShipsToJsonFile(ships, SHIPLIST_PATH);
    }

    public static void writeShipsToJsonFile( Ships ships, String shipListPath) throws IOException {

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
        return readShipsFromFile( SHIPLIST_PATH);
    }

    public static Ships readShipsFromFile(String shipListPath) throws IOException {
        try {
            Gson gson = new Gson();
            File shipListFile = new File(shipListPath);
            String readJsonShips = Files.lines(Paths.get(shipListPath))
                    .collect(Collectors.joining("\n"));
            return gson.fromJson(readJsonShips, Ships.class);
        } catch (IOException ie) {
            log.error("Failed to read Ships:", ie);
            throw ie;
        }
    }

}

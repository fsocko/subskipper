package ship.data.reader;

import com.google.gson.Gson;
import fps.subskipper.core.Ships;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;


@Slf4j
public class ShipsToJsonFileWriter {

    public static void writeShipsToJsonFile(Ships ships, String shipListPath) throws IOException {
        FileWriter writer = null;
        try {
            Gson gson = new Gson();
            String writeShips = gson.toJson(ships);
            writer = new FileWriter(shipListPath);
            writer.write(writeShips);
        } catch (IOException ie) {
            log.error("Failed to write Ships.", ie);
        } finally {
            try {
                writer.close();
            } catch (IOException ioe) {
                log.error("Failed to close writer:", ioe);
                throw ioe;
            }
        }
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

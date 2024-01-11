package fps.subskipper.recognitionManualParser.writers;

import com.x5.template.Chunk;
import com.x5.template.Theme;
import fps.subskipper.core.OutFormat;
import fps.subskipper.core.Ship;
import fps.subskipper.recognitionManualParser.util.FileIO;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;

import static fps.subskipper.util.Constants.*;

@Slf4j
public abstract class ParseRecognitionManual {

    private String currentTitle;

    protected String createRecognitionManualHead(String title, String chunkId, boolean isImperial){

        Theme theme = new Theme();
        Chunk h = theme.makeChunk(chunkId);

        if (isImperial) {
            title = title + " (Imperial Version)";
        } else {
            title = title + " (Metric Version)";
        }
        h.set("title", title);
        h.set("heading", title);

        currentTitle = title;
        return h.toString();
    }

    protected String createRecognitionManualBodyRecord(Ship record, boolean isImperial, String chunkId) {

            Theme theme = new Theme();
            Chunk h = theme.makeChunk(chunkId);
            h.set("flag", "flag"); //TODO: figure out how ships are sorted, assign flags. Maybe typeInt?
            h.set("name", record.getName());
            h.set("class", record.getTypeName());
            h.set("speed", OutFormat.builder(record.getMaxSpeed()).addUnit(UNIT_KNOT).build());

            h.set("length", OutFormat.builder(record.getLength()).addUnit(UNIT_METRE).build());
            h.set("height", OutFormat.builder(record.getMast()).addUnit(UNIT_METRE).build());
            h.set("draft", OutFormat.builder(record.getDraft()).addUnit(UNIT_METRE).build());

            if(isImperial) {
                h.set("length", OutFormat.builder(record.getLength()).metresToFeet().addUnit(UNIT_FOOT).build());
                h.set("height", OutFormat.builder(record.getMast()).metresToFeet().addUnit(UNIT_FOOT).build());
                h.set("draft", OutFormat.builder(record.getDraft()).metresToFeet().addUnit(UNIT_FOOT).build());
            }

            h.set("displacement", OutFormat.builder(record.getDisplacement()).zeroDP().addUnit(UNIT_GROSS_TONS).build());
            h.set("aspect", OutFormat.builder(record.getReferenceAspectRatio()).fourDP().build());
            h.set("image", record.getImage());

            return h.toString();
        }


    protected String createRecognitionManualTermination(String chunkId) {
            Theme theme = new Theme();
            Chunk h = theme.makeChunk(chunkId);
            return h.toString();
    }



    protected void writeRecognitionManualToFile(String input, String path) throws FileNotFoundException {
        FileIO htmlWriter = new FileIO();
        htmlWriter.writeLine(path, input);
        log.info("Wrote recognition Manual to file: " + this.currentTitle);
    }

}

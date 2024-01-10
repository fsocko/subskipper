package fps.subskipper.recognitionManualParser.writers;

import com.x5.template.Chunk;
import com.x5.template.Theme;
import fps.subskipper.core.OutFormat;
import fps.subskipper.core.Ship;
import fps.subskipper.core.Ships;
import fps.subskipper.recognitionManualParser.util.FileIO;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;

import static fps.subskipper.util.Constants.*;

//Parse data from XML parser which parses SCAF data, into long form HTML recognition manual.
@Slf4j
public class ParseRecognitionManualLong {

    //takes Ship list Ships, takes filename of doc. - Long recog manual
    //with images and data in long format, styled with CSS.
    //boolean isImperial turns relevant units to "wrong units"
    public void writeRecogLHTML(Ships shipList, String filename, boolean isImperial) throws FileNotFoundException {
        //<head>
        StringBuilder htmlDoc = new StringBuilder();
        htmlDoc.append(HTMLStartL(isImperial));

        //Main Ship HTML
        for (Ship ship : shipList.getShipList()) {
            htmlDoc.append(HTMLShipL(ship, isImperial));
        }
        //Close remaining tags
        htmlDoc.append(HTMLEndL());
        writeHTML(htmlDoc.toString(), filename);
        log.info("HTML Written.");
    }

    private String HTMLStartL(boolean isImperial) {

        String title = "Long Recognition Manual for SH4,TMO,SCAF.";
        if (isImperial) {
            title = title + " (Imperial Version)";
        } else {
            title = title + " (Metric Version)";
        }

        Theme theme = new Theme();
        Chunk h = theme.makeChunk("recogL2#start"); //Chunk used to write to HTML: mostly <head>.
        h.set("title", title); //Altering the title tag.
        h.set("heading", title); //Altering the title tag.
        //System.out.println(h.toString()); //Temporarily outputs to console. Will make it send to file.
        return h.toString();
    }

    private String HTMLShipL(Ship record, boolean isImperial) {

        Theme theme = new Theme();
        Chunk h = theme.makeChunk("recogL2#ship");
        h.set("flag", "flag"); //TODO: figure out how ships are sorted, assign flags. Maybe typeInt?
        h.set("name", record.getName());
        h.set("class", record.getTypeName());
        h.set("speed", OutFormat.addUnit(record.getMaxSpeed(), UNIT_KNOT));

        h.set("length", OutFormat.addUnit(record.getLength(), UNIT_METRE));
        h.set("height", OutFormat.addUnit(record.getMast(), UNIT_METRE));
        h.set("draft", OutFormat.addUnit(record.getDraft(), UNIT_METRE));

        if(isImperial){
            h.set("length", OutFormat.addUnit(record.getImperialLength(), UNIT_FOOT));
            h.set("height", OutFormat.addUnit(record.getImperialMast(), UNIT_FOOT));
            h.set("draft", OutFormat.addUnit(record.getImperialDraft(), UNIT_FOOT));
        }

        h.set("displacement", OutFormat.addUnit(OutFormat.zeroDP(record.getDisplacement()), UNIT_GROSS_TONS));
        h.set("aspect", OutFormat.fourDP(record.getReferenceAspectRatio()) );

        h.set("image", record.getImage());

        return h.toString(); //Temporarily outputs to console. Will make it send to file.
    }

    private String HTMLEndL() {
        Theme theme = new Theme();
        Chunk h = theme.makeChunk("recogL2#terminate");
        return h.toString();
    }

    private void writeHTML(String input, String path) throws FileNotFoundException {
        FileIO htmlW = new FileIO();
        htmlW.writeLine(path, input);
    }
}
	
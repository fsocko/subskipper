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
    //boolean imperial turns relevant units to "wrong units"
    public void writeRecogLHTML(Ships shipList, String filename, boolean imperial) throws FileNotFoundException {
        //<head>
        StringBuilder htmlDoc = new StringBuilder();
        htmlDoc.append(HTMLStartL(imperial));

        //Main Ship HTML
        for (Ship ship : shipList.getShipList()) {
            htmlDoc.append(HTMLShipL(ship, imperial));
        }
        //Close remaining tags
        htmlDoc.append(HTMLEndL());
        writeHTML(htmlDoc.toString(), filename);
        log.info("HTML Written.");
    }

    private String HTMLStartL(boolean imperial) {

        String title = "Long Recognition Manual for SH4,TMO,SCAF.";
        if (imperial) {
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

    private String HTMLShipL(Ship record, boolean imperial) {

        String unit = UNIT_METRE;

        OutFormat formatter = new OutFormat();
        Theme theme = new Theme();
        Chunk h = theme.makeChunk("recogL2#ship");
        h.set("flag", "flag"); //TODO: figure out how ships are sorted, assign flags. Maybe typeInt?
        h.set("name", record.getName());
        h.set("class", record.getTypeName());
        h.set("speed", formatter.addUnit(record.getMaxSpeed(), UNIT_KNOT));

        h.set("length", formatter.addUnit(record.getLength(), unit));
        h.set("height", formatter.addUnit(record.getMast(), unit));
        h.set("draft", formatter.addUnit(record.getDraft(), unit));

        if(imperial){
            h.set("length", formatter.addUnit(record.getImperialLength(), UNIT_FOOT));
            h.set("height", formatter.addUnit(record.getImperialMast(), UNIT_FOOT));
            h.set("draft", formatter.addUnit(record.getImperialDraft(), UNIT_FOOT));
        }

        h.set("disp", record.getDisplacement() + " GRT"); //FIXME: should be unit
        h.set("aspect", formatter.fourDP(record.getReferenceAspectRatio()));

        //TODO: implement image reader
//        //Convert image path to filename
//        String pngPath = record.getImagePath();
//        pngPath = pngPath.substring((pngPath.lastIndexOf("\\") + 1), pngPath.lastIndexOf("."));
//        String figPath = "../figures/" + pngPath + ".png";
//        h.set("image", figPath);

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
	
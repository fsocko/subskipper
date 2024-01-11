package fps.subskipper.recognitionManualParser.writers;

import com.x5.template.Chunk;
import com.x5.template.Theme;
import fps.subskipper.core.Ship;
import fps.subskipper.core.Ships;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;

import static fps.subskipper.util.Constants.*;

//Parse data from XML parser which parses SCAF data, into long form HTML recognition manual.
@Slf4j
public class ParseRecognitionManualLong extends ParseRecognitionManual {

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
        super.writeRecognitionManualToFile(htmlDoc.toString(), filename);
        log.info("Long Manual HTML Written.");
    }

    private String HTMLStartL(boolean isImperial) {

        String title = "Long Recognition Manual for SH4,TMO,SCAF.";
        if (isImperial) {
            title = title + " (Imperial Version)";
        } else {
            title = title + " (Metric Version)";
        }

        Theme theme = new Theme();
        Chunk h = theme.makeChunk(THEME_RECOGNITION_MANUAL_LONG_START); //Chunk used to write to HTML: mostly <head>.
        h.set("title", title); //Altering the title tag.
        h.set("heading", title); //Altering the title tag.
        //System.out.println(h.toString()); //Temporarily outputs to console. Will make it send to file.
        return h.toString();
    }

    private String HTMLShipL(Ship record, boolean isImperial) {
        return super.createRecognitionManualBodyRecord(record, isImperial, THEME_RECOGNITION_MANUAL_LONG_SHIP);
    }

    private String HTMLEndL() {
        return super.createRecognitionManualTermination(THEME_RECOGNITION_MANUAL_LONG_TAIL);
    }

}
	
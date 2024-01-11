package fps.subskipper.recognitionManualParser.writers;

import com.x5.template.Chunk;
import com.x5.template.Theme;
import fps.subskipper.core.OutFormat;
import fps.subskipper.core.Ship;
import fps.subskipper.core.Ships;
import fps.subskipper.util.Constants;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;

import static fps.subskipper.util.Constants.*;

//Parse data from XML parser which parses SCAF data, into short table form HTML recognition manual.
@Slf4j
public class ParseRecognitionManualShort extends ParseRecognitionManual {

    //takes Ship list Ships, takes filename of doc. - Short style
    //shipList is a Ships object, filename is the name of output file, isImperial=true converts
    //units to isImperial where relevant. AOB table generates a row of common AOB ratios.
    public void writeRecogSHTML(Ships shipList, String filename, boolean isImperial, boolean withAOBTable) throws FileNotFoundException {

        StringBuilder htmlDoc = new StringBuilder();
        htmlDoc.append(this.writeHead(isImperial));
        htmlDoc.append(this.startTable(isImperial));

        //Main Ship HTML
        for (Ship ship : shipList.getShipList()) {
            htmlDoc.append(this.shipRow(ship, isImperial));
        }

        htmlDoc.append(this.terminateTable());
        log.info("Short Manual HTML Written.");
        super.writeRecognitionManualToFile(htmlDoc.toString(), filename);
    }

    private String writeHead(boolean isImperial) {
        String title = "Short Recognition Manual for SH4,TMO,SCAF.";
        return super.createRecognitionManualHead(title, THEME_RECOGNITION_MANUAL_SHORT_HEAD, isImperial );
    }

    //start the table - return heading with correct units
    private String startTable(boolean imperial) {
        Theme theme = new Theme();
        Chunk tableStart = theme.makeChunk(THEME_RECOGNITION_MANUAL_SHORT_START_TABLE);
        if (imperial) {
            tableStart.set("unit", UNIT_FOOT);
        } else {
            tableStart.set("unit", Constants.UNIT_METRE);
        }
        return tableStart.toString();
    }


    private String shipRow(Ship record, boolean isImperial) {
        return super.createRecognitionManualBodyRecord(record, isImperial, THEME_RECOGNITION_MANUAL_SHORT_SHIP);
    }

    private String terminateTable() {
       return super.createRecognitionManualTermination(THEME_RECOGNITION_MANUAL_SHORT_TAIL);
    }

    @Deprecated //(?)
    private String AOBRow(Ship record, int i) {

        Theme theme = new Theme();
        Chunk h = theme.makeChunk(THEME_RECOGNITION_MANUAL_SHORT_AOB_ROW);

        h.set("AOBRowID", i); //This could be used for alternating colour rows.
        //Sin(AOB) * AR Ref = Aspect ratio at AOB
        h.set("10deg", OutFormat.builder(aspectAtAOB(record, 10)).fourDP().build());
        h.set("20deg", OutFormat.builder(aspectAtAOB(record, 20)).fourDP().build());
        h.set("30deg", OutFormat.builder(aspectAtAOB(record, 30)).fourDP().build());
        h.set("40deg", OutFormat.builder(aspectAtAOB(record, 40)).fourDP().build());
        h.set("50deg", OutFormat.builder(aspectAtAOB(record, 50)).fourDP().build());
        h.set("60deg", OutFormat.builder(aspectAtAOB(record, 60)).fourDP().build());
        h.set("70deg", OutFormat.builder(aspectAtAOB(record, 70)).fourDP().build());
        h.set("80deg", OutFormat.builder(aspectAtAOB(record, 80)).fourDP().build());
        h.set("90deg", OutFormat.builder(aspectAtAOB(record, 90)).fourDP().build());

        return h.toString();
    }

    @Deprecated //(?)
    //function with an AOB as a parameter, for filling out AOB table.
    private double aspectAtAOB(Ship record, int AOB) {
        double refAspect = record.getReferenceAspectRatio();
        double radAOB = Math.toRadians((double) AOB);
        double ratio = Math.sin(radAOB) * refAspect;
        return ratio;
    }

}
	
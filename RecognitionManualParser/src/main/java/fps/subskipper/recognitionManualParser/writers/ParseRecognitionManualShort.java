package fps.subskipper.recognitionManualParser.writers;

import com.x5.template.Chunk;
import com.x5.template.Theme;
import fps.subskipper.core.OutFormat;
import fps.subskipper.core.Ship;
import fps.subskipper.core.Ships;
import fps.subskipper.util.Constants;
import fps.subskipper.recognitionManualParser.util.FileIO;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;

import static fps.subskipper.util.Constants.UNIT_FOOT;

//Parse data from XML parser which parses SCAF data, into short table form HTML recognition manual.
@Slf4j
public class ParseRecognitionManualShort {

    //takes Ship list Ships, takes filename of doc. - Short style
    //shipList is a Ships object, filename is the name of output file, imperial=true converts
    //units to imperial where relevant. AOB table generates a row of common AOB ratios.
    public void writeRecogSHTML(Ships shipList, String filename, boolean imperial, boolean AOBTable) throws FileNotFoundException {

        StringBuilder htmlDoc = new StringBuilder();
        htmlDoc.append(writeHead(imperial));
        htmlDoc.append(startTable(imperial));

        //Main Ship table row
        for (int i = 0; i < shipList.getShips().size(); i++) {
            htmlDoc.append(shipRow(shipList.getShips().get(i), i, imperial));
            if (AOBTable) {
                htmlDoc.append(AOBRow(shipList.getShips().get(i), i));
            }
            if (i % 50 == 0 && i > 0) {
                htmlDoc.append(splitTable(imperial)); //split the table if this is a page break
            }
        }
        writeHTML(htmlDoc.toString(), filename);
    }


    //Write HTML Head - done once at start.
    private String writeHead(boolean imperial) {

        Theme theme = new Theme();
        Chunk h = theme.makeChunk("recogSDiv#head"); //Chunk used to write to HTML: <head> only.

        String title = "Short Recognition Manual for SH4,TMO,SCAF.";
        if (imperial) {
            title = title + " (Imperial Version)";
        } else {
            title = title + " (Metric Version)";
        }
        h.set("title", title);
        h.set("heading", title);
        return h.toString();
    }

    //start the table - return heading with correct units
    private String startTable(boolean imperial) {
        Theme theme = new Theme();
        Chunk tableStart = theme.makeChunk("recogSDiv#startTable");
        if (imperial) {
            tableStart.set("unit", UNIT_FOOT);
        } else {
            tableStart.set("unit", Constants.UNIT_METRE);
        }
        return tableStart.toString();
    }


    private String shipRow(Ship record, int i, boolean imperial) {

        OutFormat f = new OutFormat();
        Theme theme = new Theme();
        Chunk h = theme.makeChunk("recogSDiv#ship");

        h.set("rowID", i); //was: h.set("rowID", record.getId());
        /*Originally the rowID was the ID of the ship record.
         * If the records are sorted by Name, which is how I sorted
         * mine for my own use, the IDs would be all over the place.
         * Now this is set so as to ID each ship incrementally in the
         * order it appears in the HTML.
         */

        h.set("name", record.getName());
        h.set("disp", f.twoDP(record.getDisplacement()));
        h.set("speed", f.twoDP(record.getMaxSpeed()));

        h.set("draft", f.twoDP(record.getDraft()));
        h.set("length", f.twoDP(record.getLength()));
        h.set("height", f.twoDP(record.getMast()));

        if (imperial) {
            if (imperial) {
                h.set("draft", f.twoDP(record.getImperialDraft()));
                h.set("length", f.twoDP(record.getImperialLength()));
                h.set("height", f.twoDP(record.getImperialMast()));
            }
        }

        h.set("aspect", f.fourDP(record.getReferenceAspectRatio()));
        return h.toString();
    }

    private String AOBRow(Ship record, int i) {

        OutFormat f = new OutFormat();
        Theme theme = new Theme();
        Chunk h = theme.makeChunk("recogSDiv#AOBRow");

        h.set("AOBRowID", i); //This could be used for alternating colour rows.
        //Sin(AOB) * AR Ref = Aspect ratio at AOB
        h.set("10deg", f.fourDP(aspectAtAOB(record, 10)));
        h.set("20deg", f.fourDP(aspectAtAOB(record, 20)));
        h.set("30deg", f.fourDP(aspectAtAOB(record, 30)));
        h.set("40deg", f.fourDP(aspectAtAOB(record, 40)));
        h.set("50deg", f.fourDP(aspectAtAOB(record, 50)));
        h.set("60deg", f.fourDP(aspectAtAOB(record, 60)));
        h.set("70deg", f.fourDP(aspectAtAOB(record, 70)));
        h.set("80deg", f.fourDP(aspectAtAOB(record, 80)));
        h.set("90deg", f.fourDP(aspectAtAOB(record, 90)));

        return h.toString();
    }

    //function with an AOB as a parameter, for filling out AOB table.
    private double aspectAtAOB(Ship record, int AOB) {

        double refAspect = record.getReferenceAspectRatio();
        double radAOB = Math.toRadians((double) AOB);
        double ratio = Math.sin(radAOB) * refAspect;
        return ratio;
    }


    //split the table for page breaks
    private String splitTable(boolean imperial) {
        String split = "</table>\n";
        split += startTable(imperial);
        return split.toString();
    }

    private void writeHTML(String input, String path) throws FileNotFoundException {
        FileIO htmlWriter = new FileIO();
        htmlWriter.writeLine(path, input);
        log.info("Parse Recognition Manual Short : Published successfully."); //FIXME: fix
    }

}
	
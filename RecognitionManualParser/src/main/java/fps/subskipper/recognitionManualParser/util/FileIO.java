package fps.subskipper.recognitionManualParser.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.lang.invoke.MethodHandles;

public class FileIO {

    final static Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public void writeLine(String file, String text) throws FileNotFoundException {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileOutputStream(file, false));
            pw.println(text);
        }
        catch (FileNotFoundException e) {
            logger.error("Could Not Find File: {}", file);
            throw e;
        }
        finally {
            if (pw != null) {
                pw.close();
            }
        }
    }
}

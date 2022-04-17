package fps.subskipper.recognitionManualParser.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.lang.invoke.MethodHandles;

@Slf4j
public class FileIO {

    public void writeLine(String file, String text) throws FileNotFoundException {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new FileOutputStream(file, false));
            pw.println(text);
        }
        catch (FileNotFoundException e) {
            log.error("Could Not Find File:", file);
            throw e;
        }
        finally {
            if (pw != null) {
                pw.close();
            }
        }
    }
}

package coreLogic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.lang.invoke.MethodHandles;

/**
 * Separate class for text file IO.
 *
 * @author fps
 */
public class FileIO {

    final static Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public void writeLine(String file, String text) {

        try {
            PrintWriter pw = new PrintWriter(new FileOutputStream(file, false));
            pw.println(text);
            pw.close();
        } catch (FileNotFoundException e) {
            logger.error("Could Not Find File: {}", file);
            e.printStackTrace();
        }
    }
}

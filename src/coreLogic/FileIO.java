package coreLogic;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import org.apache.logging.log4j.*;

/**
 * Separate class for text file IO.
 * @author fps 
 * 
 */
public class FileIO {

	final Logger logger = LogManager.getLogger(this.getClass());

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

package coreLogic;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

//Separate class for text file IO.
public class FileIO {
	
	public void writeLine(String file, String text){
		
		try {
			PrintWriter pw = new PrintWriter(new FileOutputStream(file, false));
			pw.println(text);
			pw.close();
		} 
		catch (FileNotFoundException e) {
			System.out.println("Could Not Find File");
			e.printStackTrace();
		}
		
		
	}
	
}

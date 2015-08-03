//reads SCAF data files, converts from SH4 format to XML to be used
//by SubSkipper

package xmlParser;
import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.regex.*;

public class ReadShips {

	//findFile
	
	//parseFileToXML
	public void a1(String filename){
		
		
	}
	
	 public ArrayList readShipFile(String file)
	    { 
			System.out.print("Adding all lines of the file: " + file + " to an ArrayList.\n");
		 	ArrayList allRec = new ArrayList();
			 
			 FileInputStream fs = null;
			 try
			 { 
				 fs= new FileInputStream(file);
				 BufferedReader br = new BufferedReader(new InputStreamReader(fs));
				 
				 br.close();
			 }
			 
			 catch(FileNotFoundException F){
				 System.out.println("IOexception while reading.");}
			 
			 catch (IOException e){
	             e.printStackTrace();
	             System.out.println("could not read file.");
	         }   
			 
			 finally{
			     if (fs != null){
			    	 try{
			    		 fs.close();
			    	 }
			    	 catch(IOException e2){
			    		 System.out.println("IOException while closing.");
			    		 }
			     }	     	     
			 }
			 return allRec;		 
	    }
}

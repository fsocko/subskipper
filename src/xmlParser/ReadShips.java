//reads SCAF data files, converts from SH4 format to XML to be used
//by SubSkipper

package xmlParser;
import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.regex.*;

public class ReadShips {

/*
An Example SCAF ship record. starts with empty line, ends with 2 trailing empty lines

[Unit] -- Detect this and start reading
ClassName=COKaibokan2 -- TODO: Not sure what this does yet, need to see roster/names.cfg
3DModelFileName=data/Sea/COKaibokan/COKaibokan -- not used
UnitType=1 -- TODO: type appears in roster/names.cfg
MaxSpeed=19 
Length=89.5
Width=11.1
Mast=27.7
Draft=3.4  -- ALL WE NEED IS 8 LINES FROM THE RECORD
Displacement=900 --Not used
RenownAwarded=120 --Not used
CrewComplement=30 --Not used
SurvivalRate=70 --Not used
SurvivalPercentage=20 --Not used
*/
	   
	public String tempShip[] = new String[8];
	//array of strings read from SCAF file
	//public so other methods can edit the array later.
	
	
	//read from the file and construct a single Ship object.
	public void readShip(String file){
		
		
		
		 FileInputStream fs = null;
		 try{ 
			 fs= new FileInputStream(file);
			 BufferedReader br = new BufferedReader(new InputStreamReader(fs));
			 int i =0;
			 String curLine = "";
			 int append = 0; // incremented when adding to tempShip array so we don't add nulls.
			 while(!(curLine.contains("Displacement"))){
				 
				 curLine = br.readLine().trim();
				 
				 //TODO: set a boolean to make sure file is valid
				 if(curLine.contains("[Unit]")){//checks if the record is valid
					 curLine = br.readLine().trim(); //move to next line after check
					 System.out.println("Valid Record, Start index now.");	 
				 }
				 
				 //We have to pass a file path argument either way. Not sure how consistent
				 //the 3D model path field is compared to the imagePath.
				 if(curLine.contains("3DModel")){
					 curLine = br.readLine().trim();
				 }
				 
				 if(curLine.contains("Displacement")){
					 break;
				 }
				 
				 //start saving to temp array of lines. Each array creates one ship
				 else if(!(curLine.equals(null) || curLine.equals(""))){
					 
					 tempShip[append] = curLine;
					 append ++;
				 }
				 
				 i++;
				 }
			
			 //Print tempShip to make sure there's no mistake.
			 for(int j = 0; j<tempShip.length -1; j++){
				 if(j==0){System.out.println("\n\n Starting tempShip dump:");}
				 System.out.println(j + " " + tempShip[j]);
			 }
				
		 }
		 catch(FileNotFoundException F){
			 System.out.println("IOexception while reading.");}
		 
		 catch (IOException e){
             e.printStackTrace();
             System.out.println("could not read file.");
         }   
	}
	
	//call methods for formatting public array tempShip into format suitable for Ship.class
	//probably OK to change values directly in array.
			
}



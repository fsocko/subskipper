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
Draft=3.4  -- ALL WE NEED IS 7 LINES FROM THE RECORD
Displacement=900 --Not used
RenownAwarded=120 --Not used
CrewComplement=30 --Not used
SurvivalRate=70 --Not used
SurvivalPercentage=20 --Not used
*/
	//Luckily, this data is all in metric. 
	
	
	   
	private String tempShips[] = new String[8]; //accessible only to this class.
	//array of strings read from SCAF file
	//public so other methods can edit the array later.
	
	
	//read from the file and construct a single Ship object.
	public void readShip(String file){

		 FileInputStream fs = null;
		 try{ 
			 fs= new FileInputStream(file);
			 BufferedReader br = new BufferedReader(new InputStreamReader(fs));
			 String curLine = "";
			 int append = 0; // incremented when adding to tempShips array so we don't add nulls.
			 while(!(curLine.contains("Renown"))){
				 
				 curLine = br.readLine().trim();
				 
				 //TODO: set a boolean to make sure file is valid
				 if(curLine.contains("[Unit]")){//checks if the record is valid
					 curLine = br.readLine().trim(); //move to next line after check	 
				 }
				 
				 //We have to pass a file path argument either way. Not sure how consistent
				 //the 3D model path field is compared to the imagePath.
				 if(curLine.contains("3DModel")){
					 curLine = br.readLine().trim();
				 }
				 
				 if(curLine.contains("RenownAwarded")){
					 break;
				 }
				 
				 //start saving to temp array of lines. Each array creates one ship
				 //Skip any blank lines.
				 else if(!(curLine.equals(null) || curLine.equals("") || curLine.equals("null"))){
					 
					 tempShips[append] = curLine;
					 append ++;
				 }
			 }
			
		 }
		 catch(FileNotFoundException F){
			 System.out.println("IOexception while reading.");}
		 
		 catch (IOException e){
             e.printStackTrace();
             System.out.println("could not read file.");
         }   
		
		printTempShips(); //print results.
		stripVars(); //run method for stripping SCAF artefacts.
		printTempShips(); //print results.
	}
	
	//methods for formatting public array tempShips into format suitable for Ship.class
	//after that construct an instance of the ship, to be later parsed to XML.
	//takes no arguments
	public void stripVars(){ //Strips incompatible data from array.
		String curTempShip = "";
		for(int i = 0; i<tempShips.length;i++){ //go through all array cells.
			curTempShip = tempShips[i];
			curTempShip = curTempShip.substring(curTempShip.indexOf("=")+1, curTempShip.length());
			tempShips[i] = curTempShip;
		}
	}
	
	public void printTempShips(){
		 //Print tempShips to make sure there's no mistake.
		 for(int j = 0; j<tempShips.length; j++){
			 if(j==0){System.out.println("\n\n Starting tempShips dump:");}
			 System.out.println(j + " " + tempShips[j]);
		 }
	}
	
	
	public void formatShip(){
		
		
		
		Ship SCAFShip = new Ship();
	}
}//EOF


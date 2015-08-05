//reads SCAF data files, converts from SH4 format to XML to be used
//by SubSkipper

package xmlParser;
import java.io.*;
import java.nio.file.Path; //TODO: don't remember what this is for, it's never used.
import java.util.Arrays;
import java.util.ArrayList;
import java.util.regex.*;

public class ReadShips {

/*
An Example SCAF ship record. starts with empty line, ends with 2 trailing empty lines

[Unit] -- Detect this and start reading
ClassName=COKaibokan2
3DModelFileName=data/Sea/COKaibokan/COKaibokan -- not used
UnitType=1 -- Not Used
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
	private String namesPath = "shipData/Roster/Names.cfg"; //path to Names file
	
	public ArrayList<File> shipFiles = new ArrayList<File>();
	
	public void printShipFiles(){
		listf("shipData\\Sea", shipFiles);
		for(int i = 0; i < shipFiles.size(); i++){
			System.out.println(shipFiles.get(i).toString());
		}
	}
	  
	public void listf(String directoryName, ArrayList<File> files) {
	    File directory = new File(directoryName);

	    // recursively list files in directory and sub directories.
	    File[] fList = directory.listFiles();
	    for (File file : fList){
	        if (!((file.toString().toLowerCase().contains("walleye")) || 
	        	 (file.toString().toLowerCase().contains("nde_parker")) ||
	        	 (file.toString().toLowerCase().contains("ryuun"))) 
	        	 && file.isFile() && file.toString().toLowerCase().endsWith(".cfg")){        
	        	//file extension filter. We're only interested in .cfg
	        	//Also, filters out ships which for whatever reason have bad data in SCAF
	            files.add(file);
	        } else if (file.isDirectory()) {
	            listf(file.getPath(), files);
	        }
	    }
	}
	
	//TODO: RYUUN maru? it seems to have been broken in SCAF. Ditto Walleye
	//read from the file and return an array of useful lines from SCAF record.
		private String[] readShipRecord(String file){ //TODO: set this back to private after test.
		String[] tempShips = new String[8];
		 FileInputStream fs = null;
		 try{ 
			 fs= new FileInputStream(file);
			 BufferedReader br = new BufferedReader(new InputStreamReader(fs));
			 String curLine = "";
			 int append = 0; // incremented when adding to tempShips array so we don't add nulls.
			 while(br.ready()){ 
				 
				 curLine = br.readLine().trim();
				 //SCAF files use semiColon for comments. This breaks everything.
				 if(curLine.contains(";")){
					 curLine = curLine.substring(0, curLine.indexOf(";")-1);
				 }
				 
				 
				 if(curLine.contains("[Unit]")){//checks if the record is valid
					 curLine = br.readLine().trim(); //move to next line after check	 
				 }
				 
				 //Skip this line as it is unused.
				 if(curLine.contains("3DModel")){
					 curLine = br.readLine().trim();
				 }
				 
				 //We extracted all the useful records.
				 if(curLine.contains("Renown") || curLine.contains("DisplacementVariation")){
					 br.close();
					 break;
				 }
				 
				 //start saving to temp array of lines. Each array creates one ship
				 //Skip any blank lines.
				 else if(!(curLine.equals(null) || curLine.equals("") || curLine==null)){
					 
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
	
		 return tempShips;
	}
	
	//methods for formatting public array tempShips into format suitable for Ship.class
	//after that construct an instance of the ship, to be later parsed to XML.
	//takes no arguments
	public void stripVars(String[] tempShips){ //Strips incompatible data from array created by readShips
		String curTempShip = "";
		for(int i = 0; i<tempShips.length;i++){ //go through all array cells.
			
			if(tempShips[i]==null){
				break;
			}
			
			else{
				curTempShip = tempShips[i];
				curTempShip = curTempShip.substring(curTempShip.indexOf("=")+1, curTempShip.length());
				tempShips[i] = curTempShip;
			}
		}
	}
	
	public void printTempShip(String path){
		System.out.println(Arrays.toString(readShipRecord(path)));
	}
	
	//This method formats the type Number, and uses nameLookup to return a typeName
	public String typeLookup(String typeNum){
		String typeName = "";
		return typeName;
	}
	
	//this method looks up a query from names.cfg using a linear line-by-line search.
	//takes the short className from the Ship file as its input, and returns a stripped ship name.
	public String typeNameLookup(String typeNum){
		typeNum = "Type" + typeNum.trim();
		return nameLookup(typeNum);
	}
	
	public String nameLookup(String query){
		boolean found = false;
		String curLine = "ReadShips.nameLookup() failed";
		FileInputStream fs = null;
				 
		 try{ 
			 fs= new FileInputStream(namesPath);
			 BufferedReader br = new BufferedReader(new InputStreamReader(fs));
			 
			 while(!found){
				 if (! br.ready()){
					 System.out.println("Reached Names.cfg EOF. Breaking.");
					 break;}

				 curLine = br.readLine().trim();
				 
				 if(curLine.contains(query)){ //we found the name, change tempShips[0]
						curLine = curLine.substring(curLine.indexOf("=")+1, curLine.length());
						br.close();
						found = true;
				 }
			 }
		 }
		 catch(FileNotFoundException F){
			 System.out.println("IOexception while reading.");}
		 
		 catch (IOException e){
             e.printStackTrace();
             System.out.println("could not read file.");
         }   
		 
		 if(found){
			 return curLine;
		 }
		 
		 else{
			 return curLine + " | ERROR: \"" +query +"\" not found in ReadShips.nameLookup().";}
		 //this ought to show up in XML if nameLookup fails, and make debugging easier
	}

	//Format and construct a ship object using data in tempShips
	public Ship makeShip(String path){
		String [] tempShips = readShipRecord(path);
		stripVars(tempShips); //remove descriptor strings.
		
		String name = nameLookup(tempShips[0]);
		int type = Integer.parseInt(tempShips[1]);
		String typeName = typeNameLookup(tempShips[1]);
		
		String imagePath = path.substring(0, path.length()-4);
		//this cuts off the last 4 letters in the string. Usually, this would be .cfg, but it might not be. 
		//TODO: implement a better solution for finding the imagePath.
		imagePath += "_sil.dds";
		
		double maxSpeed = Double.parseDouble(tempShips[2]);
		double length = Double.parseDouble(tempShips[3]);
		double width = Double.parseDouble(tempShips[4]);
		double mast = Double.parseDouble(tempShips[5]);
		double draft = Double.parseDouble(tempShips[6]);
		double disp = Double.parseDouble(tempShips[7]);
		
		Ship testShip = new Ship(name, type, typeName, imagePath, maxSpeed, length, width, mast, draft, disp);
		return testShip;
	}
	
	
}//EOF


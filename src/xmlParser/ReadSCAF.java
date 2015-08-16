//reads SCAF data files, converts from SH4 format to XML to be used
//by SubSkipper

package xmlParser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import coreLogic.Ship;


public class ReadSCAF {

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
	//TODO: make namesPath searchable, rather than hardcoded
	private String namesPath = "data/SCAF for TMO_2/Data/Roster/Names.cfg"; //path to Names file
	
	
	//recursively goes through directories, filters out ship cfg files.
	public void listFile(String directoryName, ArrayList<File> files) {
	    File directory = new File(directoryName);
	    // recursively list files in directory and sub directories.
	    File[] fList = directory.listFiles();
	    for (File file : fList){
	        if (file.isFile() &&
	        	//Exclude these:
	        	!((file.toString().toLowerCase().contains("walleye")) || 
	        	 (file.toString().toLowerCase().contains("nde_parker")) ||
	        	 (file.toString().toLowerCase().contains("ryuun")) ||
	        	 (file.toString().toLowerCase().contains("cargodef")) ||
	        	 (file.toString().toLowerCase().contains("roster")))
	        	 //file extension filter. We're only interested in .cfg 
	        	 && file.toString().toLowerCase().endsWith(".cfg")
	           )
	        {        
	            files.add(file);
	        } else if (file.isDirectory()) {
	            listFile(file.getPath(), files);
	        }
	    }
	}
	
		private String[] readShipRecord(String file){
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
		//First run listF
		ArrayList<File> shipFiles = new ArrayList<File>();
		listFile("data", shipFiles);
		String [] tempShips = readShipRecord(path);
		stripVars(tempShips); //remove descriptor strings.
		
		String name = nameLookup(tempShips[0]);
		int type = Integer.parseInt(tempShips[1]);
		String typeName = typeNameLookup(tempShips[1]);
		
		String imagePath = path.substring(0, path.length()-4);
		//this cuts off the last 4 letters in the string. Usually, this would be .cfg, but it might not be. 
		//TODO: implement a better solution for finding the imagePath.
		imagePath += "_sil.dds";
		imagePath = "\"" + imagePath + "\"";
		
		double maxSpeed = Double.parseDouble(tempShips[2]);
		double length = Double.parseDouble(tempShips[3]);
		double width = Double.parseDouble(tempShips[4]);
		double mast = Double.parseDouble(tempShips[5]);
		double draft = Double.parseDouble(tempShips[6]);
		double disp = Double.parseDouble(tempShips[7]);
		
		Ship testShip = new Ship(name, type, typeName, imagePath, maxSpeed, length, width, mast, draft, disp);
		return testShip;
	}
	//Prints all useful SCAF files.
	public void printSCAFFiles(){
		ArrayList<File> shipFiles = new ArrayList<File>();
		System.out.println("\nPrint all ships:\n************************************************************\n");
		listFile("data", shipFiles);
		for(int i = 0; i < shipFiles.size(); i++){
			System.out.println(shipFiles.get(i).toString());
		}
	}
	
	//Returns an arrayList of Ship objects, for passing to WriteShipXML
	public ArrayList<Ship> makeShips(){
		ArrayList<File> shipFiles = new ArrayList<File>();
		ArrayList<Ship> shipData = new ArrayList<Ship>();
		Ship writeShip = new Ship();
		
		listFile("data", shipFiles);
		
		for(int i = 0; i < shipFiles.size(); i++){
			writeShip = makeShip(shipFiles.get(i).toString());
			writeShip.setID(i);
			shipData.add(writeShip);
		}
		return shipData;
	}
}//EOF


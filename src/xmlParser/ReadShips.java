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
	
	private String tempShips[] = new String[8]; //Array of useful strings copied from SCAF file.
	//later, a Ship object is constructed from this data.
	
	
	private String namesPath = "shipData/Roster/Names.cfg"; //path to Names file
	
	
	//read from the file and construct a single Ship object.
	public void readShipRecord(String file){

		 FileInputStream fs = null;
		 try{ 
			 fs= new FileInputStream(file);
			 BufferedReader br = new BufferedReader(new InputStreamReader(fs));
			 String curLine = "";
			 int append = 0; // incremented when adding to tempShips array so we don't add nulls.
			 while(br.ready()){ 
				 
				 curLine = br.readLine().trim();
				 if(curLine.contains("[Unit]")){//checks if the record is valid
					 curLine = br.readLine().trim(); //move to next line after check	 
				 }
				 
				 //Skip this line as it is unused.
				 if(curLine.contains("3DModel")){
					 curLine = br.readLine().trim();
				 }
				 
				 //We extracted all the useful records.
				 if(curLine.contains("Renown")){
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
	}
	
	//methods for formatting public array tempShips into format suitable for Ship.class
	//after that construct an instance of the ship, to be later parsed to XML.
	//takes no arguments
	public void stripVars(){ //Strips incompatible data from array.
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
	
	public void printTempShips(){
		 //Print tempShips to make sure there's no mistake.
		 for(int j = 0; j<tempShips.length; j++){
			 if(j==0){System.out.println("\n\n Starting tempShips dump:");}
			 System.out.println(j + " " + tempShips[j]);
		 }
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
	public Ship makeShip(){
		//public Ship(String name, int type, String typeName, String imagePath, double maxSpeed, 
		//double length, double width, double mast, double draft, double disp)
		String name = nameLookup(tempShips[0]);
		int type = Integer.parseInt(tempShips[1]);
		String typeName = typeNameLookup(tempShips[1]);
		String imagePath = "A PATH."; //TODO: implement recursive ship browsing.
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


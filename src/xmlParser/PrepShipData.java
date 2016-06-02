package xmlParser;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import coreLogic.Ship;

public class PrepShipData {

	
	//This method reads from SCAF, generates a ship XML file and 
	//returns an arrayList of Ship objects. It also implements
	//error checks in case for any reason a Ships can't be returned.
	//If there is an error, a null is returned.
	
	
	//return a prepared Ships object if possible from XML. Otherwise, parseSCAF
	//and write / read XML
	public Ships getShips(){
		
		ReadShipXML readShips = new ReadShipXML();
		Ships parsedShips = new Ships();
		
		if(!readShips.readXML().equals(null)){
			parsedShips = readShips.readXML();
			System.out.println("XML read successfully.");
		}
		else{parsedShips = FullDataCycle();}
		
		return parsedShips;
	}
	
	
	//This method is run if there is no readily available XML file. 
	//It runs both SCAF to XML as well as subsequently reading the
	//resulting file
	private Ships FullDataCycle(){
		WriteShipData();
		ReadShipXML readShips = new ReadShipXML();
		Ships fcShips = new Ships();
		fcShips = readShips.readXML();
		return fcShips;
	}
	
	//Writes SCAF to xml
	private void WriteShipData(){
			
		//Look up and parse data from SCAF to XML file
		ReadSCAF scaf = new ReadSCAF();
		Ships runShips = new Ships();
		runShips.setShips(scaf.makeShips()); //we have a loaded Ships object
		WriteShipXML writeShips = new WriteShipXML();
		writeShips.writeXML(runShips); //written to XML
		System.out.println("shipList.xml file parsed from SCAF.");
	}
	

	//Look up a ship by an ID
	public Ship getShipByID(int shipID){
		return getShipByID(shipID, getShips());
	}
	
	public Ship getShipByID(int shipID, Ships shipsData){
		
		ArrayList<Ship> shipList = shipsData.getShips();
		Ship idShip = new Ship();
		
		for(int i = 0; i<shipList.size(); i++){
			if(shipList.get(i).getID() == shipID){
				 idShip = shipList.get(i); 
			}
		}
		return idShip;
	}
		
	//Prints contents of a Ships
	public void printShips(Ships printShips){
		
		for(int i = 0; i < printShips.getShips().size(); i++){
			System.out.println(printShips.getShip(i).toString());
		}
	}
	
	public void sortedList(Ships shipRecords, String property){
		
	}

}//EOF

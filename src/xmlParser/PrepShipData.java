package xmlParser;

public class PrepShipData {

	
	//This method reads from SCAF, generates a ship XML file and 
	//returns an arrayList of Ship objects
	
	public void WriteShipData(){
		
		//Look up and parse data from SCAF to XML file
		ReadSCAF scaf = new ReadSCAF();
		Ships runShips = new Ships();
		runShips.setShips(scaf.makeShips()); //we have a loaded Ships object
		WriteShipXML writeShips = new WriteShipXML();
		writeShips.writeXML(runShips); //written to XML
		System.out.println("shipList.xml file ready");
	}
	
	//Returns a Ships obj from a ready XML file
	public Ships getShipData(){
		
		ReadShipXML readShips = new ReadShipXML();
		System.out.println("********************************READING XML***********");
		Ships runShips = new Ships();
		runShips = readShips.readXML();
		return runShips; // Reads shipData.xml, returns Ships object
	}
	
	//This method is run if there is no readily available XML file. 
	//It runs both SCAF to XML as well as subsequently reading the
	//resulting file
	public Ships FullDataCycle(){
		WriteShipData();
		ReadShipXML readShips = new ReadShipXML();
		Ships fcShips = new Ships();
		fcShips = readShips.readXML();
		return fcShips;
	}
	
	
	//Prints contents of a Ships
	public void printShips(Ships printShips){
		
		for(int i = 0; i < printShips.getShips().size(); i++){
			System.out.println(printShips.getShips().get(i).toString());
		}
	}
}

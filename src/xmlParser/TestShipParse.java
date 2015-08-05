package xmlParser;

public class TestShipParse {

	public static void main(String[] args) {

		//readShips from file:
		ReadShips readTest = new ReadShips();
		readTest.readShipRecord("shipData/Sea/NCA_Mogami/NCA_Mogami.cfg");

		readTest.printTempShips(); //print results.
		readTest.stripVars(); //run method for stripping SCAF artefacts.
		System.out.println("\n" + readTest.nameLookup(readTest.tempShips[0])); //find the name for this ship
		System.out.println("\n" + readTest.typeNameLookup(readTest.tempShips[1])); //find the name of the ship type
		readTest.printTempShips(); //print results.
		
	}

}

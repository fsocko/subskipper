package xmlParser;

public class TestShipParse {

	public static void main(String[] args) {

		//readShips from file:
		ReadShips readTest = new ReadShips();
		readTest.readShipRecord("shipData/Sea/COKaibokan2/COKaibokan2.cfg");
		readTest.printTempShips();
		Ship USSEnterprise = readTest.makeShip();
		System.out.println(USSEnterprise.toString());

		
	}

}

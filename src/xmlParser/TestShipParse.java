package xmlParser;

public class TestShipParse {

	public static void main(String[] args) {

		//readShips from file:
		ReadShips readTest = new ReadShips();
		readTest.printTempShip("shipData/Sea/COKaibokan2/COKaibokan2.cfg");
		Ship USSEnterprise = readTest.makeShip("shipData/Sea/COKaibokan2/COKaibokan2.cfg");
		
		System.out.println(USSEnterprise.toString());

		
	}

}

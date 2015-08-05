package xmlParser;

public class TestShipParse {

	public static void main(String[] args) {

		//readShips from file:
		ReadShips readTest = new ReadShips();
		readTest.readShipRecord("shipData/Sea/NCA_Mogami/NCA_Mogami.cfg");
		Ship USSEnterprise = readTest.makeShip();
		System.out.println(USSEnterprise.toString());
		
	}

}

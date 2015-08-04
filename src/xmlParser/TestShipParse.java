package xmlParser;

public class TestShipParse {

	public static void main(String[] args) {

		//readShips from file:
		ReadShips readTest = new ReadShips();
		readTest.readShip("shipData/Sea/AuxSubchaser/AuxSubchaser.cfg");

	}

}

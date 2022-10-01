package ship.data.reader;

import fps.subskipper.core.Ships;

public class Main {

	public static void main(String[] args) {
		
		try {
			ReadShipsFromSh4DataImpl sr = new ReadShipsFromSh4DataImpl();
			Ships testShips = sr.parseShipsFromScaf();
			
			System.out.println(testShips.toString());
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}

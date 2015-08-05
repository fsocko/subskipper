package xmlParser;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class TestShipParse {

	public static void main(String[] args) {

		ReadShips readTest = new ReadShips();

		//create and print objects of all available .cfg files.
		readTest.printShipFiles();
		
		
		for(int i = 0; i < readTest.shipFiles.size(); i++){
			System.out.println(readTest.makeShip(readTest.shipFiles.get(i).toString()).toString());
		}

		
	}

}

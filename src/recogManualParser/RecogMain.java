package recogManualParser;

import java.io.IOException;

import xmlParser.PrepShipData;

public class RecogMain {

	public static void main(String[] args) {
		
		ParseRecogL recogL = new ParseRecogL();
		ParseRecogS recogS = new ParseRecogS();
		
			PrepShipData target = new PrepShipData();
			recogL.writeRecogLHTML(target.sortShipsName(target.getShips()), "recog/recogHTML/recogLSN.html", false);
			recogL.writeRecogLHTML(target.sortShipsType(target.getShips()), "recog/recogHTML/recogLST.html", false);
			
			recogS.writeRecogSHTML(target.sortShipsName(target.getShips()), "recog/recogHTML/recogS.html", false);
			
			
	}

}

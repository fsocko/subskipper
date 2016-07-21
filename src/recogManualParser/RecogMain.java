package recogManualParser;

import java.io.IOException;

import xmlParser.PrepShipData;

public class RecogMain {

	public static void main(String[] args) {
		
		ParseRecogL recogL = new ParseRecogL();
		ParseRecogS recogS = new ParseRecogS();
		PrepShipData target = new PrepShipData();
	
		
		//long Euro / Name Sort
		//recogL.writeRecogLHTML(target.sortShipsName(target.getShips()), "recog/recogHTML/recogLSNm.html", false);
		//long Imperial / Name Sort
		//recogL.writeRecogLHTML(target.sortShipsName(target.getShips()), "recog/recogHTML/recogLSNi.html", true);
		//long Euro / Type Sort
		//recogL.writeRecogLHTML(target.sortShipsType(target.getShips()), "recog/recogHTML/recogLSTm.html", false);
		//long Imperial / Type Sort
		//recogL.writeRecogLHTML(target.sortShipsType(target.getShips()), "recog/recogHTML/recogLSTi.html", true);
		

		//short Euro
		recogS.writeRecogSHTML(target.sortShipsName(target.getShips()), "recog/recogHTML/recogSm.html", false, true);
		//Short Imperial
		recogS.writeRecogSHTML(target.sortShipsName(target.getShips()), "recog/recogHTML/recogSi.html", true, true);
		
	}

}

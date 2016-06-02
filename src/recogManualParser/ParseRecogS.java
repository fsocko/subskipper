package recogManualParser;

import java.io.IOException;

import com.x5.template.Chunk;
import com.x5.template.Theme;

import coreLogic.FileIO;
import coreLogic.OutFormat;
import coreLogic.Ship;
import xmlParser.PrepShipData;
import xmlParser.Ships;
//Parse data from XML parser which parses SCAF data, into short table form HTML recognition manual.

public class ParseRecogS {

	public static void main(String[] args) throws IOException {
		
		PrepShipData target = new PrepShipData();
		writeRecogSHTML(target.sortShipsName(target.getShips()), "recog/recogHTML/recogS.html", false);
	}
		
		//takes Ship list Ships, takes filename of doc. - Short style
		public static void writeRecogSHTML(Ships shipList, String filename, boolean imperial){
			
			String htmlDoc = "";
			
			Theme theme = new Theme();
			Chunk h = theme.makeChunk("recogS#start"); //Chunk used to write to HTML: mostly <head>.
			h.set("title", "Recognition Manual (SH4, TMO, SCAF)." ); //Altering the title tag.
			htmlDoc += h.toString();
			
			Chunk r = theme.makeChunk("recogS#table"); //Chunk used to write to HTML: mostly <head>.
			
			//Main Ship table row
			for(int i = 0; i < shipList.getShips().size(); i++)
			{
				htmlDoc += shipRowHTML(shipList.getShip(i), imperial);
			}
			
			Chunk t = theme.makeChunk("recogS#terminate"); //end the table and page
			htmlDoc += t.toString();
			
			System.out.println(htmlDoc);
			writeHTML(htmlDoc, filename);
		}
		
		private static String shipRowHTML(Ship record, boolean imperial){
			
			if(imperial){
				record.makeImperial();
			}
			
			OutFormat f = new OutFormat();
			Theme theme = new Theme();	
			Chunk h = theme.makeChunk("recogS#ship");
			h.set("name", record.getName());
			h.set("disp", record.getDisp());
			h.set("speed", record.getMaxSpeed());
			h.set("draft", record.getDraft());
			h.set("length", record.getLength());
			h.set("height", record.getMast());
			h.set("aspect", f.fourDP(record.getRefAspect()));
			return h.toString();
		}
		
		private static void writeHTML(String input, String path){
			
			FileIO htmlW = new FileIO();
			htmlW.writeLine(path, input);
			System.out.println("HTML Written.");
		}
	}
	
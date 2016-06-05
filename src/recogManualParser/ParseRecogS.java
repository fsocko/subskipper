package recogManualParser;

import com.x5.template.Chunk;
import com.x5.template.Theme;

import coreLogic.FileIO;
import coreLogic.OutFormat;
import coreLogic.Ship;
import xmlParser.Ships;
//Parse data from XML parser which parses SCAF data, into short table form HTML recognition manual.

public class ParseRecogS {
	
		//takes Ship list Ships, takes filename of doc. - Short style
		public void writeRecogSHTML(Ships shipList, String filename, boolean imperial){
			
			String htmlDoc = "";
			Theme theme = new Theme();
			Chunk h = theme.makeChunk("recogS#start"); //Chunk used to write to HTML: mostly <head>.
			String title = "Short Recognition Manual for SH4,TMO,SCAF.";
			if(imperial){
				title = title + " (Imperial Version)";
				h.set("unit", "(ft)");
			}
				else{
					title = title + " (Metric Version)";
					h.set("unit", "(m)");
				}
			
			h.set("title", title);
			h.set("heading", title);
			
			htmlDoc += h.toString();
			
			Chunk r = theme.makeChunk("recogS#table"); //Chunk used to write to HTML: mostly <head>.
			
			//Main Ship table row
			for(int i = 0; i < shipList.getShips().size(); i++)
			{
				htmlDoc += shipRowHTML(shipList.getShip(i), imperial);
			}
			
			Chunk t = theme.makeChunk("recogS#terminate"); //end the table and page
			htmlDoc += t.toString();
			writeHTML(htmlDoc, filename);
		}
		
		private String shipRowHTML(Ship record, boolean imperial){
			
			OutFormat f = new OutFormat();
			Theme theme = new Theme();	
			Chunk h = theme.makeChunk("recogS#ship");
			
			if(imperial){
				record.makeImperial();
			}
			
			h.set("name", record.getName());
			h.set("disp", f.twoDP(record.getDisp()));
			h.set("speed", f.twoDP(record.getMaxSpeed()));
			h.set("draft", f.twoDP(record.getDraft()));
			h.set("length", f.twoDP(record.getLength()));
			h.set("height", f.twoDP(record.getMast()));
			h.set("aspect", f.fourDP(record.getRefAspect()));
			return h.toString();
		}
		
		private void writeHTML(String input, String path){
			
			FileIO htmlW = new FileIO();
			htmlW.writeLine(path, input);
			System.out.println("HTML Written.");
		}
	}
	
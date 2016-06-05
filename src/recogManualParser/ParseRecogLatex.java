package recogManualParser;

import com.x5.template.Chunk;
import com.x5.template.Theme;
import coreLogic.FileIO;
import coreLogic.OutFormat;
import coreLogic.Ship;
import xmlParser.Ships;
//Parse data from XML parser which parses SCAF data, into short table form HTML recognition manual.

public class ParseRecogLatex {
	
		//takes Ship list Ships, takes filename of doc. - Short style
		public void writeRecogLat(Ships shipList, String filename, boolean imperial){
			
			writeLatex((preamble(imperial) + startTable(imperial) + 
			allShips(shipList, imperial) + endTable() + terminate()), filename, false);
			//last var is for printing to console 
		}
		
		private String preamble(boolean imperial){
			String doc = "";
			Theme theme = new Theme();
			Chunk h = theme.makeChunk("recogLatex2#preamble"); //Chunk used to write to HTML: mostly <head>.
			String title = "Short Recognition Manual for SH4,TMO,SCAF.";
			if(imperial){
				title = title + " (Imperial)";
				h.set("unit", "(ft)");
			}
				else{
					title = title + " (Metric)";
					h.set("unit", "(m)");
				}
			
			h.set("title", title);
			doc += h.toString();
			return doc;
		}
		
		private String startTable(boolean imperial){
			String doc = "";
			Theme theme = new Theme();
			Chunk h = theme.makeChunk("recogLatex2#startT"); //Chunk used to write to HTML: mostly <head>.
			String title = "Short Recognition Manual for SH4,TMO,SCAF.";
			if(imperial){
				title = title + " (Imperial)";
				h.set("unit", "(ft)");
			}
				else{
					title = title + " (Metric)";
					h.set("unit", "(m)");
				}
			
			doc += h.toString();
			return doc;
		}
		
		private String allShips(Ships shipList, boolean imperial)
		{
			String doc = "";
			for(int i = 0; i < shipList.getShips().size(); i++)
			{
				if(i % 55 == 0 && i > 0){doc += endTable() + "\\pagebreak\n" + startTable(imperial);}
				doc += shipRow(shipList.getShip(i), imperial);
			}
			
			return doc;
		}
		
		private String shipRow(Ship record, boolean imperial){
			
			OutFormat f = new OutFormat();
			Theme theme = new Theme();	
			Chunk h = theme.makeChunk("recogLatex2#ship");
						
			if(imperial){
				record.makeImperial();
			}
			
			String name = record.getName().replaceAll("&", "."); //can't have & in latex.
			h.set("name", name);
			h.set("disp", f.twoDP(record.getDisp()));
			h.set("speed", f.twoDP(record.getMaxSpeed()));
			h.set("draft", f.twoDP(record.getDraft()));
			h.set("length", f.twoDP(record.getLength()));
			h.set("height", f.twoDP(record.getMast()));
			h.set("aspect", f.fourDP(record.getRefAspect()));
			return h.toString();
		}
		
		private String endTable(){
			String doc = "";
			Theme theme = new Theme();
			Chunk h = theme.makeChunk("recogLatex2#endT");			
			doc += h.toString();
			return doc;
		}
		
		private String terminate(){
			Theme theme = new Theme();
			Chunk h = theme.makeChunk("recogLatex2#terminate");			
			return h.toString();
		}
		
		private void writeLatex(String input, String path, boolean print){
			
			FileIO htmlW = new FileIO();
			htmlW.writeLine(path, input);
			if(print){System.out.println(input);}
			System.out.println("Latex Written.");
		}
	}
	
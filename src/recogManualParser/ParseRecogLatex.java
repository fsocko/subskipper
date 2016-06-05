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
			writeLatex(preamble(imperial), filename, true);
		}
		
		private String preamble(boolean imperial)
		{
			String htmlDoc = "";
			Theme theme = new Theme();
			Chunk h = theme.makeChunk("recogLatex#preamble"); //Chunk used to write to HTML: mostly <head>.
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
			htmlDoc += h.toString();
			return htmlDoc;
		}
		
		private void writeLatex(String input, String path, boolean print){
			
			FileIO htmlW = new FileIO();
			htmlW.writeLine(path, input);
			if(print){System.out.println(input);}
			System.out.println("HTML Written.");
		}
	}
	
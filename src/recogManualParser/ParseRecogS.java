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
		//shipList is a Ships object, filename is the name of output file, imperial=true converts 
		//units to imperial where relevant. AOB table generates a row of common AOB ratios.
		public void writeRecogSHTML(Ships shipList, String filename, boolean imperial, boolean AOBTable){
			
			String htmlDoc = "";
			htmlDoc += writeHead(imperial);
			htmlDoc += startTable(imperial);
						
			//Main Ship table row
			for(int i = 0; i < shipList.getShips().size(); i++)
			{
				htmlDoc += shipRow(shipList.getShip(i), i, imperial);
				if(AOBTable){htmlDoc += AOBRow(shipList.getShip(i), i);}
				
				if(i % 50 == 0 && i > 0){
					htmlDoc += splitTable(imperial); //split the table if this is a page break
				}
			}
			
			
			writeHTML(htmlDoc, filename);
		}
		
		//Write HTML Head - done once at start.
		private String writeHead(boolean imperial){
			
			Theme theme = new Theme();
			Chunk h = theme.makeChunk("recogS#head"); //Chunk used to write to HTML: <head> only.
			
			String title = "Short Recognition Manual for SH4,TMO,SCAF.";
			if(imperial){
				title = title + " (Imperial Version)";
			}
				else{
					title = title + " (Metric Version)";		
			}
			h.set("title", title);
			h.set("heading", title);
			return h.toString();
		}
		
		
		//start the table - return heading with correct units
		private String startTable(boolean imperial){
			
			Theme theme = new Theme();
			Chunk tableStart = theme.makeChunk("recogS#startTable");
			if(imperial){
				tableStart.set("unit", "(ft)");
			}
			else {tableStart.set("unit", "(m)");}
			
			return tableStart.toString();
		}
		
		
		
		private String shipRow(Ship record, int i, boolean imperial){
			
			OutFormat f = new OutFormat();
			Theme theme = new Theme();	
			Chunk h = theme.makeChunk("recogS#ship");
			
			if(imperial){
				record.makeImperial();
			}
			
			h.set("rowID", i); //was: h.set("rowID", record.getID());  
			/*Originally the rowID was the ID of the ship record.
			 * If the records are sorted by Name, which is how I sorted
			 * mine for my own use, the IDs would be all over the place.
			 * Now this is set so as to ID each ship incrementally in the
			 * order it appears in the HTML.
			 */
			
			h.set("rowClass", "shipRow"); //This could be used for alternating colour rows.
			h.set("name", record.getName());
			h.set("disp", f.twoDP(record.getDisp()));
			h.set("speed", f.twoDP(record.getMaxSpeed()));
			h.set("draft", f.twoDP(record.getDraft()));
			h.set("length", f.twoDP(record.getLength()));
			h.set("height", f.twoDP(record.getMast()));
			h.set("aspect", f.fourDP(record.getRefAspect()));
			return h.toString();
		}
		
		private String AOBRow(Ship record, int i){
			
			OutFormat f = new OutFormat();
			Theme theme = new Theme();	
			Chunk h = theme.makeChunk("recogS#AOBRow");
			
			h.set("AOBRowID", i); //This could be used for alternating colour rows.
			h.set("AOBRowClass", "AOBRow"); //This could be used for alternating colour rows.
			 //Sin(AOB) * AR Ref = Aspect ratio at AOB
			h.set("10deg", f.fourDP(Math.sin(10) * record.getRefAspect()));
			h.set("20deg", f.fourDP(Math.sin(20) * record.getRefAspect()));
			h.set("30deg", f.fourDP(Math.sin(30) * record.getRefAspect()));
			h.set("40deg", f.fourDP(Math.sin(40) * record.getRefAspect()));
			h.set("50deg", f.fourDP(Math.sin(50) * record.getRefAspect()));
			h.set("60deg", f.fourDP(Math.sin(60) * record.getRefAspect()));
			h.set("70deg", f.fourDP(Math.sin(70) * record.getRefAspect()));
			h.set("80deg", f.fourDP(Math.sin(80) * record.getRefAspect()));
			h.set("90deg", f.fourDP(Math.sin(90) * record.getRefAspect()));
			
			return h.toString();
			
		}
		
		
		//split the table for page breaks
		private String splitTable(boolean imperial){
			String split = "</table>\n";
			split += startTable(imperial);
			return split.toString();
		}
		
		
		private void writeHTML(String input, String path){
			
			FileIO htmlW = new FileIO();
			htmlW.writeLine(path, input);
			System.out.println("HTML Written.");
		}
	}
	
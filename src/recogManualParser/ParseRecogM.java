package recogManualParser;

import java.io.IOException;

import com.x5.template.Chunk;
import com.x5.template.Theme;

import coreLogic.FileIO;
import coreLogic.OutFormat;
import coreLogic.Ship;
import xmlParser.PrepShipData;
import xmlParser.Ships;

//Parse data from XML parser which parses mod data, into a LATEX format in order to
//create a paper or digital and accurate recognition manual.

public class ParseRecogM {

	public static void main(String[] args) throws IOException {

		//Get the ship objects	
		//target.printShips(target.getShips());
		writeAllHTML();
	}
		
		public static void writeAllHTML(){
			
						
			//<head>
			String htmlDoc = HTMLStart();
			
			//Main Ship HTML
			PrepShipData target = new PrepShipData();
			Ships shipList = target.getShips();
			for(int i = 0; i < shipList.getShips().size(); i++)
			{
				
				htmlDoc += HTMLShip(shipList.getShip(i));
			}
			//Close remaining tags
			htmlDoc += HTMLEnd();
			writeHTML(htmlDoc);
			
			System.out.println("HTML Written.");
		}
		
		private static String HTMLStart(){
			
			Theme theme = new Theme();
			Chunk h = theme.makeChunk("recogL2#start"); //Chunk used to write to HTML: mostly <head>.
			h.set("title", "Recognition Manual (SH4, TMO, SCAF)." ); //Altering the title tag.
			//System.out.println(h.toString()); //Temporarily outputs to console. Will make it send to file.
			return h.toString();
		}
		
		private static String HTMLShip(Ship record){
		 
			OutFormat f = new OutFormat();
			Theme theme = new Theme();	
			Chunk h = theme.makeChunk("recogL2#ship");
			h.set("flag", "flag"); //TODO: figure out how ships are sorted, assign flags. Maybe typeInt?
			h.set("name", record.getName());
			h.set("class", record.getTypeName());
			h.set("speed", f.addUnit(record.getMaxSpeed(), "kn"));
			h.set("length", f.addUnit(record.getLength(), "m"));
			h.set("height", f.addUnit(record.getMast(), "m"));
			h.set("draft", f.addUnit(record.getDraft(), "m"));
			h.set("disp", record.getDisp() + " GRT");
			h.set("aspect", f.fourDP(record.getRefAspect()));
			
			//Convert image path to filename
			String pngPath = record.getImagePath();
			pngPath = pngPath.substring((pngPath.lastIndexOf("\\") + 1), pngPath.lastIndexOf("."));
			String figPath = "../figures/" + pngPath + ".png";	
			h.set("image", figPath);
			return h.toString(); //Temporarily outputs to console. Will make it send to file.	

		}
		
		private static String HTMLEnd(){
			Theme theme = new Theme();	
			Chunk h = theme.makeChunk("recogL2#terminate");
			return h.toString();
		}

		private static void writeHTML(String input){
			
			FileIO htmlW = new FileIO();
			htmlW.writeLine("recog/recogHTML/recogL2.html", input);
		}
	}
	
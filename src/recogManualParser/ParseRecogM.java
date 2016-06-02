package recogManualParser;

import com.x5.template.Theme;

import coreLogic.Ship;
import xmlParser.PrepShipData;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

import com.x5.template.Chunk;

//Parse data from XML parser which parses mod data, into a LATEX format in order to
//create a paper or digital and accurate recognition manual.

public class ParseRecogM {

	public static void main(String[] args) throws IOException {

		//Get the ship objects	
		PrepShipData target = new PrepShipData();
		writeHTML(target.getShipByID(12));
	}

		public static void writeHTML(Ship record){
		 
		Theme theme = new Theme();
		Chunk h = theme.makeChunk("recogL#start"); //Chunk used to write to HTML: mostly <head>.
		h.set("title", "Recognition Manual (SH4, TMO, SCAF)." ); //Altering the title tag.
		System.out.println(h.toString()); //Temporarily outputs to console. Will make it send to file.
		
		h = theme.makeChunk("recogL#ship");
		h.set("flag", "flag"); //TODO: figure out how ships are sorted, assign flags. Maybe typeInt?
		h.set("name", record.getName());
		h.set("class", record.getTypeName());
		h.set("speed", record.getMaxSpeed());
		h.set("length", record.getLength());
		h.set("height", record.getMast());
		h.set("draft", record.getDraft());
		h.set("disp", record.getDisp());
		h.set("aspect", record.getRefAspect());
		
		//Convert image path to filename
		String pngPath = record.getImagePath();
		pngPath = pngPath.substring((pngPath.lastIndexOf("\\") + 1), pngPath.lastIndexOf("."));
		String figPath = "..//figures//" + pngPath + ".png";	
		h.set("image", figPath);
		System.out.println(h.toString()); //Temporarily outputs to console. Will make it send to file.
		
		
		h = theme.makeChunk("recogL#terminate");
		
		System.out.println(h.toString()); //Temporarily outputs to console. Will make it send to file.
		
		}
		
		
		public void writeShipLatex(Ship record, boolean image){
			
			/* //Convert image path to filename
			String recordImgPath = record.getImagePath();
			recordImgPath = recordImgPath.substring(recordImgPath.lastIndexOf("\\") + 1);
			*/
			String name = record.getName();
			name = name.replaceAll("&", ".");
			try{
			PrintWriter pw = new PrintWriter(new FileOutputStream("recog/out.txt", true));
			pw.println(name +"& $" + record.getMaxSpeed() +"$ & $"+ record.getLength() +"$ & $"+ record.getWidth()
			+"$ & $"+ record.getMast() +"$ & $"+ record.getDraft() +"$ & $"+ twoDP(record.getRefAspect()) + "$ \\" + "\\");
			}
			
			 
			 catch (IOException e){
	             e.printStackTrace();
	             System.out.println("could not read file.");
	         } 
		}
		
		 public String twoDP(double twoDP){
		   	 
		    	DecimalFormat formatter = new DecimalFormat("0.0000");
		    	String out = formatter.format(twoDP);
		    	return out;
		    }
		

	}
	
	
	

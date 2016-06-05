package recogManualParser;

import java.text.DecimalFormat;

import coreLogic.FileIO;
import coreLogic.OutFormat;
import coreLogic.Ship;
import xmlParser.PrepShipData;
import xmlParser.Ships;

//Parse data from XML parser which parses SCAF data, into a LATEX format in order to
//create a paper or digital Short Form recognition manual.

public class ParseRecogLatex {

	public void writeRecogLatex(Ships shipList){
		
		startTableL("asd");
		
		int i = 0;
		while(i < 118){
			if(i==0){startDoc(); System.out.println("startTable");}
			writeShipTableL(shipList.getShips());
			i ++;
		}
		tableEnd();
	}
		
	
	private void startTableL(String file){
	}
		
	private void writeShipTableL(Ship record){
		
		OutFormat DP = new OutFormat();
		FileIO pw = new FileIO();
		
		
		String name = record.getName();
		name = name.replaceAll("&", ".");
		//pw.println("\\hline");
		String latexRec = name +"& $" + record.getMaxSpeed() +"$ & $"+ record.getLength() +"$ & $"+ record.getWidth()
		+"$ & $"+ record.getMast() +"$ & $"+ record.getDraft() +"$ & $"+ DP.fourDP(record.getRefAspect()) + "$ \\" + "\\";
	
		pw.writeLine("recog/out.txt", latexRec);
	}
	
	private void tableEnd(){
		
		//PrintWriter pw = new PrintWriter(new FileOutputStream("recog/out.txt", true));
		//pw.println("\\hline");
		//pw.println("\\end{tabular}");	
		//pw.println("\\end{document}");

	}
	
}//EOF
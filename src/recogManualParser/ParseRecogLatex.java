package recogManualParser;

import java.io.*;
import java.nio.file.Files;
import java.text.DecimalFormat;

import com.x5.template.Chunk;
import com.x5.template.Theme;

import coreLogic.OutFormat;
import coreLogic.Ship;
import xmlParser.PrepShipData;
import xmlParser.Ships;

//Parse data from XML parser which parses SCAF data, into a LATEX format in order to
//create a paper or digital Short Form recognition manual.

public class ParseRecogLatex {

	public void writeRecogLatex(Ships shipList){

		
		int i = 0;
		while(i < 118){
			if(i==0){startDoc(); System.out.println("startTable");}
			writeShipLatex(shipList.getShips().size());
			i ++;
		}
		tableEnd();
	}
	
	public void writeShipLatex(Ships shipList){
		
	}
	
	
	private void startDoc(){
		try{
			PrintWriter pw = new PrintWriter(new FileOutputStream("recog/out.txt", false));
			//preamble
			pw.println("%%--Custom Recognition Manual");
			pw.println("%%--Modified 10.05.16");
			pw.println("\\documentclass{article}");
			pw.println("%%--packages");
			pw.println("\\usepackage{hyperref}");
			pw.println("\\usepackage{listings}");
			pw.println("\\usepackage{color}");
			pw.println("\\usepackage{graphicx}");
			pw.println("\\graphicspath{{figures/}}");
			pw.println("\\usepackage{geometry}");
			pw.println("%%--Define Margins");
			pw.println(" \\geometry{");
			pw.println(" a4paper,");
			pw.println(" total={170mm,257mm},");
			pw.println(" left=10mm,");
			pw.println(" right=10mm,");
			pw.println(" top=10mm,");
			pw.println(" bottom=10mm,");
			pw.println(" }");
			pw.println("\\author{Filip Socko}");
			pw.println("\\begin{document}");
			//end preamble
			
			pw.println("\\section{Recognition Manual (Short)}");
			pw.println("\\centering");
			pw.close();
		}
		 catch (IOException e){
             e.printStackTrace();
             System.out.println("could not read file.");
         }
	}	
	
    
	private void writeShipLatex(Ship record, boolean image){
		
		OutFormat DP = new OutFormat();
		
		String name = record.getName();
		name = name.replaceAll("&", ".");
		try{
		PrintWriter pw = new PrintWriter(new FileOutputStream("recog/out.txt", true));
		pw.println("\\hline");
		pw.println(name +"& $" + record.getMaxSpeed() +"$ & $"+ record.getLength() +"$ & $"+ record.getWidth()
		+"$ & $"+ record.getMast() +"$ & $"+ record.getDraft() +"$ & $"+ DP.fourDP(record.getRefAspect()) + "$ \\" + "\\");
		pw.println();		
		pw.close();
		}
		 catch (IOException e){
             e.printStackTrace();
             System.out.println("could not read file.");
         } 
	}
	
	//should be docEnd
	private static void tableEnd(){
		
		try{
		PrintWriter pw = new PrintWriter(new FileOutputStream("recog/out.txt", true));
		pw.println("\\hline");
		pw.println("\\end{tabular}");	
		pw.println("\\end{document}");
		pw.close();
		}
		 
		 catch (IOException e){
             e.printStackTrace();
             System.out.println("could not read file.");
         } 
	}
	
}//EOF
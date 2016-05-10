package recogManualParser;

import java.io.*;
import java.nio.file.Files;
import java.text.DecimalFormat;

import coreLogic.OutFormat;
import coreLogic.Ship;
import xmlParser.PrepShipData;

//Parse data from XML parser which parses mod data, into a LATEX format in order to
//create a paper or digital and accurate recognition manual.

public class ParseRecogM {

	public static void main(String[] args) {

		//Get the ship objects	
		PrepShipData target = new PrepShipData();
		
		int i = 0;
		while(i < 118){
			if(i==0){startDoc(); System.out.println("startTable");}
			if(i % 20 == 0){midTable(i);}
			writeShipLatex(target.getShipByID(i), false);
			i ++;
		}
		
		tableEnd();
	}
	
	
	public static void startDoc(){
		
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
	
    public static String twoDP(double twoDP){
   	 
    	DecimalFormat formatter = new DecimalFormat("0.0000");
    	String out = formatter.format(twoDP);
    	return out;
    }
    
	public static void writeShipLatex(Ship record, boolean image){
		
		/* //Convert image path to filename
		String recordImgPath = record.getImagePath();
		recordImgPath = recordImgPath.substring(recordImgPath.lastIndexOf("\\") + 1);
		*/
		String name = record.getName();
		name = name.replaceAll("&", ".");
		try{
		PrintWriter pw = new PrintWriter(new FileOutputStream("recog/out.txt", true));
		pw.println("\\hline");
		pw.println(name +"& $" + record.getMaxSpeed() +"$ & $"+ record.getLength() +"$ & $"+ record.getWidth()
		+"$ & $"+ record.getMast() +"$ & $"+ record.getDraft() +"$ & $"+ twoDP(record.getRefAspect()) + "$ \\" + "\\");
		pw.println();		
		pw.close();
		}
		
		 
		 catch (IOException e){
             e.printStackTrace();
             System.out.println("could not read file.");
         } 
	}
	
	public static void midTable(int i){
		
		try{
		PrintWriter pw = new PrintWriter(new FileOutputStream("recog/out.txt", true));
		
		if(i > 0){pw.println("\\hline"); pw.println("\\end{tabular}");}
		
		pw.println("\\begin{tabular}{|l|c|c|c|c|c|c|}");
		pw.println("Name & Max Speed & Length & Width & Mast Height & Draft & Aspect" +"\\" + "\\");
		pw.close();
		}
		 
		 catch (IOException e){
             e.printStackTrace();
             System.out.println("could not read file.");
         } 
	}

	
	//should be docEnd
	public static void tableEnd(){
		
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

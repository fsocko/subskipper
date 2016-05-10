package recogManualParser;

import java.io.*;
import java.nio.file.Files;

import coreLogic.Ship;
import xmlParser.PrepShipData;

//Parse data from XML parser which parses mod data, into a LATEX format in order to
//create a paper or digital and accurate recognition manual.

public class ParseRecogM {

	public static void main(String[] args) {

		//Get the ship objects
		PrepShipData target = new PrepShipData();
		Ship record = target.getShipByID(30);
		writeShipLatex(record);
	}
	
	/*
	private String name;
	private String typeName;
	private String imagePath;
	private double maxSpeed;
	private double length;
	private double width;
	private double mast;
	private double draft;
	private double refAspect;
	*/
	
	public static void writeShipLatex(Ship record){
		
		//Convert image path to filename
		String recordImgPath = record.getImagePath();
		recordImgPath = recordImgPath.substring(recordImgPath.lastIndexOf("\\") + 1);
		
		
		try{
		PrintWriter pw = new PrintWriter(new FileWriter("recog/out.txt"));
		pw.println("\\section{" + record.getName() + "}");
		pw.println("\\begin{centering}");
		pw.println("\\includegraphics[width=0.8\\textwidth, height=0.25\\textheight]{" + recordImgPath + "}");
		pw.println("\\end{centering}");
		pw.println("\\begin{tabular}{|l|c|c|c|c|c|}");
		pw.println("\\hline");
		pw.println("maxSpeed & length & width & mast & draft & refAspect \\ \\"); 
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

package recogManualParser;

//import xmlParser.PrepShipData;
import com.x5.template.Theme;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import com.x5.template.Chunk;

//Parse data from XML parser which parses mod data, into a LATEX format in order to
//create a paper or digital and accurate recognition manual.

public class ParseRecogM {

	public static void main(String[] args) throws IOException {

		//Get the ship objects	
		//PrepShipData target = new PrepShipData();
		/*
		int i = 0;
		while(i < 118){
			//if(i==0){startDoc(); System.out.println("startTable");}
			//if(i % 50 == 0){midTable(i);}
			i ++;
		}*/



		Theme theme = new Theme();
		 
		// Fetch template from this file: themes/examples/loop.chtml
		// Inside that file there is a template "snippet" named #example_1
		Chunk html = theme.makeChunk("loop#example_1");
		 
		html.set("list", new String[]{"apples","bananas","carrots","durian"} );
		 
		System.out.println(html.toString());
		
		

	}
}	
	
	

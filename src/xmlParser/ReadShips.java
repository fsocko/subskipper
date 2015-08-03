//reads SCAF data files, converts from SH4 format to XML to be used
//by SubSkipper

package xmlParser;
import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.regex.*;

public class ReadShips {

/*
An Example SCAF ship record. starts with empty line, ends with 2 trailing empty lines

[Unit] -- Detect this and start reading
ClassName=COKaibokan2 -- TODO: Not sure what this does yet, need to see roster/names.cfg
3DModelFileName=data/Sea/COKaibokan/COKaibokan -- not used
UnitType=1 -- TODO: type appears in roster/names.cfg
MaxSpeed=19 
Length=89.5
Width=11.1
Mast=27.7
Draft=3.4  -- ALL WE NEED IS 8 LINES FROM THE RECORD
Displacement=900 --Not used
RenownAwarded=120 --Not used
CrewComplement=30 --Not used
SurvivalRate=70 --Not used
SurvivalPercentage=20 --Not used
*/
	   
	
	
	
	//read from the file and construct a single Ship object.
	public void readShip(String file){
		
		String tempShip[] = new String[10];//array of strings read from SCAF file
		
		 FileInputStream fs = null;
		 try{ 
			 fs= new FileInputStream(file);
			 BufferedReader br = new BufferedReader(new InputStreamReader(fs));
			 int i =0;
			 while(i<9){
				 String curLine = br.readLine().trim();
				 //System.out.println("Curent Line:" +curLine);
				 if(curLine.contains("[Unit]")){//checks if the record is valid
					 i = 0; //index from [Unit]
					 curLine = "a null?"; //TODO: just format the strings from index 1 to index 8.
					 System.out.println("Valid Record, Start index now.");	 
				 }
				 /*
				 if(curLine.contains("3DModel")){ //we want to skip over the line containing 3DModel filepath
					 i++; //TODO: why does this make the array cell null?
				 }
				 */
				 
				 if(curLine.contains("Displacement")){ //we have reached the last useful line.
					 break;
				 }
				 
				 //start saving to temp array of lines. Each array creates one ship
				 else if(!(curLine.equals(null) || curLine.equals(""))){
					 tempShip[i] = curLine;
				 }
				 
				 i++; 
				 }
			
			 //Print tempShip to make sure there's no mistake.
			 for(int j = 0; j<tempShip.length -1; j++){
				 if(j==0){System.out.println("Starting tempShip dump:");}
				 System.out.println(tempShip[j]);
			 }
				
		 }
		 catch(FileNotFoundException F){
			 System.out.println("IOexception while reading.");}
		 
		 catch (IOException e){
             e.printStackTrace();
             System.out.println("could not read file.");
         }   
	}
	
	
			
}

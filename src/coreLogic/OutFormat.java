package coreLogic;
import java.text.*;

//String aString =  String.format("%03d", "0.00123332");
public class OutFormat {

	    public String degreeOut(double degIn){
 
	    	DecimalFormat formatter = new DecimalFormat("000");
	    	String out = formatter.format(degIn) + "\u00b0";
	    	return out;
	    }
	    
	    public String knotOut(double knotIn){
	    	 
	    	DecimalFormat formatter = new DecimalFormat("0.00");
	    	String out = formatter.format(knotIn) + "kn";
	    	return out;
	    }

	    
	
}

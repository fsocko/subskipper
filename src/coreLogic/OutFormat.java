package coreLogic;
import java.text.*;

//String aString =  String.format("%03d", "0.00123332");
public class OutFormat {

	    public String degreeOut(double degIn){
 
	    	DecimalFormat formatter = new DecimalFormat("000");
	    	String out = formatter.format(degIn) + "\u00b0";
	    	return out;
	    }
	    
	    //convert from metre per second to knot
	    public String msToKnot(double msIn){
	    	DecimalFormat formatter = new DecimalFormat("0.00");
	    	String out = formatter.format(msIn*1.94384449) + " kn";
	    	return out;
	    }
	    
	    public String metreOut(double metreIn){
	    	 
	    	DecimalFormat formatter = new DecimalFormat("0.00");
	    	String out = formatter.format(metreIn) + " m";
	    	return out;
	    }
	    
	    public String hourOut(double secondIn){
	    	 
	    		    	
	    	double hoursOut = secondIn / 3600;
	    	if(hoursOut < 1){
	    		hoursOut = 0;
	    	}
	        double minutesOut = (secondIn % 3600) / 60;
	        double secondOut = secondIn % 60;
	        DecimalFormat roundTime = new DecimalFormat("00");
	    	return (roundTime.format(hoursOut) +":"+ roundTime.format(minutesOut)+":"+roundTime.format(secondOut));
	    }

	    
	
}

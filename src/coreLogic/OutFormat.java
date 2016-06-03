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
	    public String msToKnot(double ms){
	    	double kt = ms * 1.94384449;
	    	String out = addUnit(ms, "kt");
	    	return out;
	    }
	    
	    //convert from m to ft
	    public String mToFt(double m){
	    	double kt = m * 3.28084;
	    	String out = addUnit(m, "ft");
	    	return out;
	    }
	    
	    
	    //Add Units to a double-2DP
	    public String addUnit(double i, String u){
	    	DecimalFormat f = new DecimalFormat("0.00");
	    	String out = f.format(i) + " " + u;
	    	return out;
	    }
	    
	    
	  

	    
	    
	    //Two figures after decimal
	    public String twoDP(double twoDP){
	    	 
	    	DecimalFormat formatter = new DecimalFormat("0.00");
	    	String out = formatter.format(twoDP);
	    	return out;
	    }
	    
	    //Four figures after decimal
	    public String fourDP(double fourDP){
	    	 
	    	DecimalFormat formatter = new DecimalFormat("0.0000");
	    	String out = formatter.format(fourDP);
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

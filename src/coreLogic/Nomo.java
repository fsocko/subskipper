package coreLogic;

public class Nomo {
		
	//Nomograph Logic:
	//Inputs: speed (m/s), distance(m), time(s)
	//Returns a string which contains the missing value.
	//Missing value must be a double 0.
	public String nomo(double s, double d, double t){
		
		OutFormat nomOut = new OutFormat();
		
		double speed = s;
		double dist = d;
		double time = t;
		
		if(s == 0){
			speed = d/t;
		}
		else if(d == 0){
			dist = s*t;
		}
		else if(t == 0){
			time = d/s;
		}
		return ("Speed: "+nomOut.msToKnot(speed)+"\nDistance: "+nomOut.addUnit(dist, "m")+ "\nTime: " + nomOut.hourOut(time)+"\n\n");
	}
	
}

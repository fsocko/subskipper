package coreLogic;

public class Nomo {
	
	//Nomograph Logic:
	//Inputs: speed (knots), distance(m), time(s)
	
	public double nomoVal(double s, double d, double t){
		
		double wantedVal = -1;
		if(s == 0){
			wantedVal = d/t;
		}
		else if(d == 0){
			wantedVal = s*t;
		}
		else if(t == 0){
			wantedVal = d/s;
		}
		return wantedVal;
	}
	
}

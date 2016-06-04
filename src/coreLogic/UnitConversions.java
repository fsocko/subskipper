package coreLogic;

public class UnitConversions {

	//Speed: Meter per second to knot
	//1 metre per second =	1.94384449 knot
	public double msKn(double ms){
		double knot = ms*1.94384449;
		return knot;
	}
	
	//Knot to Kph
	//1knots= 1.8520000kph
	public double knKmh(double knot){
		double kph = knot*1.8520000;
		return kph;
	}

	//m per Minute to knot
	//1 metre per minute = 0.0323974082 knot
	public double mMKn(double mM){
		double kn = mM*0.0323974082;
		return kn;
	}
	
	
}

/**
 * 
 */
package coreLogic;

import java.lang.Math;
/**
 * @author fps
 *
 */
//Lead Angle for O'Kane firing solution. - note that method does
//not know whether to add or subtract ie if T is facing port or
//stbd
//Inputs: Torpedo Speed (kn), target Speed (kn)
public class OKane {
	
	
	//TODO: check range to target is not too large for the solution
	//Method for generating OKane firing solution, returns firing bearing.
	//Inputs: AOB- determines port or strbd, Speed (F/S) default to slow; torpedo object
	public double oKSolution(int AOB, double targS, char selSpeed, Torpedo fireTorp)
	{
		double solBearing = -1;
		boolean stbd = true;
		//check if the position is correct. Sub needs to be in front of target, on either
		//Stbd or port side. this means AOB is either 0-90 for stbd or  270-360 for port
		//if anything else happens, solution is invalid, and we return a flag.
		boolean invalidSol = false;
		if(AOB <= 90 && AOB<360){
			stbd = true;
		}
		else if(AOB>=270 && AOB<360 ){
			stbd = false;
		}
		else if(AOB == 0){
			invalidSol = true; //for now let's assume the user is an idiot if AOB = 0
		}
		else{invalidSol = true;}
		
		//Input correct speed to solution based on user input
		double torpFireS = 0; //torpSpeed in knots
		switch (selSpeed){
		//TODO: what if there's no fast setting?
			case 'f':{
				torpFireS = fireTorp.getSpeedF(); //fast setting
				if (torpFireS < 0){
					torpFireS = fireTorp.getSpeedS();
					System.out.println("No Fast Speed available.");
					//if there is no fast speed, set it to slow: the user is an idiot.
				}
			}
			
			break;
			case 's': torpFireS = fireTorp.getSpeedS(); //slow setting
			break;
		}
		
		if(invalidSol == true){
			solBearing = -1;
		}
		//if stbd, subtract from 360.
		else if(stbd == true){
			solBearing = 360-okaneLead(torpFireS, targS);
		}
		//If port, add to 0 for lead bearing.
		else if(stbd == false){
			solBearing = 0 + okaneLead(torpFireS, targS);
		}
		
		
		return solBearing; //tSpeed into TDC, set your scope to this bearing, fire
		}
	
	//Input: torpedo speed (kn), target speed (kn)
	public double okaneLead(double torpS, double targS){
		if(torpS < 1 || targS <1){
			return -1;}
		double lead = 0;
		//90 - inverseTan(torpS/targS)
		lead =  90-Math.toDegrees(Math.atan(torpS/targS));
		if(lead > 90){
			lead = -1;} //Okane relies on being ahead of the target,
						//we would be aiming backwards.
		return lead;
	}
}

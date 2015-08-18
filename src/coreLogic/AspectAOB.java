package coreLogic;
import xmlParser.*;
import java.util.ArrayList;
import java.lang.Math;

//Method for determining the AOB based on an observed and reference aspect ratio.
//Note, this method requires an approximate visual AOB or it will return wildly
//inaccurate figures, because that's how trigonometry works.
public class AspectAOB {
	
	//method for getting shipData for a specific Ship from XML
	public Ship getShip(int shipID){
		PrepShipData shipData = new PrepShipData();
		Ships shipList = shipData.FullDataCycle();
		ArrayList<Ship> selShip = shipList.getShips();
		return selShip.get(shipID);
	}
	
	//Wrapper for calculateAOB, calculates AOB bearing with AOB estimate, calculates
	//observed aspect ratio
	public double aspectAOBSol(int AOBEstimate, Ship target, double mastObs, double lenObs){
		//TODO: //estAOB quadrants: 0-90, 90-180, 180-270, 270-360
		return 0;
	}
	
	//Inputs: AOB estimate: We need have a general idea what AOB is, see the doc 
	//target - a Ship object, the target. We take aspectReference from it
	//mastObs, lenObs - takes observed units, calculates ratio
	//Returns: a double containing accurate AOB. to be formatted with OutFormat
	public double calculateAOB(Ship target, double obsAR){
		
		//Reference Aspect Ratio, from Ship data
		double refAR = target.getRefAspect(); 
		double AOB = Math.toDegrees(Math.asin(obsAR/refAR));
		return AOB;
	}
	
}

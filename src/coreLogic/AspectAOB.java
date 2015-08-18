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
	
	//Wrapper for calculateAOB, calculates AOB bearing with AOB estimate checking against
	//a visual estimate of AOB due to the limits of this method.
	public double aspectAOBSol(int AOBEstimate, Ship target, double mastObs, double lenObs){
		//TODO: //estAOB quadrants: 0-90, 90-180, 180-270, 270-360
		double obsAR = lenObs / mastObs;
		return 0;
		
	}
	
	//calculates the AOB, does not account for front/back or port/stbd
	public double calculateAOB(Ship target, double obsAR){
		
		//Reference Aspect Ratio, from Ship data
		double refAR = target.getRefAspect(); 
		double AOB = Math.toDegrees(Math.asin(obsAR/refAR));
		return AOB;
	}
	
}

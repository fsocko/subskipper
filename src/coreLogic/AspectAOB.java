package coreLogic;
import xmlParser.*;
import java.util.ArrayList;

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
	
	public double calculateAOB(int estAOB, Ship target, double mastObs, double lenObs){
		return 0;
	}
	
}

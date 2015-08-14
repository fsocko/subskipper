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
        public double OKSolution(int AOB, double targS, double torpFireS)
        {
            double solBearing = -1;

            boolean stbd = true;
            //check if the position is correct. Sub needs to be in front of target, on either
            //Stbd or port side. this means AOB is either 0-90 for stbd or  270-360 for port
            //if anything else happens, solution is invalid, and we return a flag.
            boolean invalidSol = false;
            
    		if(AOB == 0){
                invalidSol = true; //for now let's assume the user is an idiot if AOB = 0
    		}
    		else if(AOB <= 90 && AOB<360){
                stbd = true;
            }
            else if(AOB>=270 && AOB<360 ){
                stbd = false;
            }
            
            else{invalidSol = true;}

            if(invalidSol){
                return solBearing;
            }
            
            //Check if OKaneLead returns an error
            double lead = okaneLead(torpFireS, targS);
            if(lead == -3){
            	return -1;
            }
           
            //if stbd, subtract from 360.
            else if(stbd){
                solBearing = 360-okaneLead(torpFireS, targS);
            }
            //If port, add to 0 for lead bearing.
            else{
                solBearing = 0 + okaneLead(torpFireS, targS);
            }
            return solBearing; //tSpeed into TDC, set your scope to this bearing, fire
        }

        //Input: torpedo speed (kn), target speed (kn)
        private double okaneLead(double torpS, double targS){
            if((torpS < 0) || (targS <0)){
                return -3;}
            //If speed is 0, no lead required
            if(targS == 0){
            	return 0;
            }
            double lead = 0;
            //90 - inverseTan(torpS/targS)
            lead =  90-Math.toDegrees(Math.atan(torpS/targS));
            if(lead > 90){
                lead = -3;} //Okane relies on being ahead of the target,
            //we would be aiming backwards.

            return lead;
        }
}

package coreLogic;
import xmlParser.*;
import java.util.ArrayList;
import java.io.File;
import java.lang.Math;

//Method for determining the AOB based on an observed and reference aspect ratio.
//Note, this method requires an approximate visual AOB or it will return wildly
//inaccurate figures, because that's how trigonometry works.
public class AspectAOB {
	

	//Wrapper for calculateAOB, calculates AOB bearing with AOB estimate checking against
	//a visual estimate of AOB due to the limits of this method.
	
	//Errors: -1: NaN found in observed AR
	// 		  -2: Observed aspect > Ref Aspect
	
	public double aspectAOB(int estAOB, Ship target, double mastObs, double lenObs){
		double obsAR = 0;
		//Check if it's a number. It's possible that NaN will be input by user.
		if(!Double.isNaN((lenObs / mastObs))){
			obsAR = lenObs / mastObs;
		}
		else{
			return -1;}
		
		double AOB = 0;
		double aspAOB = calculateAOB(target, obsAR);

		if (estAOB >=0 && estAOB <= 90){
			AOB = aspAOB;
		}
		else if(estAOB >90 && estAOB <= 180){
			AOB = 180-(aspAOB);
		}
		else if(estAOB >180 && estAOB <= 270){
			AOB = 360-(180-(aspAOB));
		}
		else if(estAOB >270 && estAOB < 360){
			AOB = 360-(aspAOB);
		}
		return AOB;
	}
	

	//calculates the AOB, does not account for front/back or port/stbd
	private double calculateAOB(Ship target, double obsAR){
		//Reference Aspect Ratio, from Ship data
		double refAR = target.getRefAspect();
		double AOB = Math.toDegrees(Math.asin(obsAR/refAR));
		if(!Double.isNaN(AOB)){
			return AOB;
		}
		else{
			return -2;
			}
		
	}
	
	
	
	
	
	
}

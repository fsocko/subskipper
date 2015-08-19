package coreLogic;
import xmlParser.PrepShipData;

public class MethodTest {

	public static void main(String[] args) 
	{
		OutFormat toDeg = new OutFormat();
		AspectAOB testAOB = new AspectAOB();
		int estAOB = 0;
		double mastObs = 0.0;
		double lenObs = 0.0;
		PrepShipData target = new PrepShipData();
		
		System.out.println(target.getShipByID(17).toString());
		System.out.println((testAOB.aspectAOBSol(estAOB, target.getShipByID(17), mastObs, lenObs)));
		
	}
	
	
	
	
	public static void OKaneTest(){
		
		OKane test1 = new OKane();
		//constructs a torpedo. INPUTS: name, slow/TODO:med/fast speed (kn), rangeS, TODO: rangeMedium, rangeF (m)
		Torpedo mk10 = new Torpedo("Mk. 10", 36, -1, 3200, -1);
		Torpedo mk14 = new Torpedo("Mk. 14", 31, 46, 8200, 4100);
		Torpedo mk16 = new Torpedo("Mk. 16", 46, -1, 12500, -1);
		
		//Format to degrees
		OutFormat toDeg = new OutFormat();
		//Okane parameters
		int AOB =95;
		double targS = 6;
		double torpFireS = 46;

		//360AOB, 0-60 targS, 
		System.out.println("*********************AOBSweep: \n");
		for(AOB = 0; AOB<360; AOB++){
			torpFireS = 46;
			targS = 12;
			System.out.println("AOB: " + AOB + " targSpeed: " + targS +" TorpSpeed: " +torpFireS);
			System.out.println("Scope Bearing: " + toDeg.degreeOut(test1.OKSolution(AOB, targS, torpFireS)));
		}
		System.out.println("*********************targS sweep: \n");	
		for(targS = -1; targS < 70; targS++)
		{
			torpFireS = 46;
			AOB = 275;
			System.out.println("AOB: " + AOB + " targSpeed: " + targS +" TorpSpeed: " +torpFireS);
			System.out.println("Scope Bearing: " + toDeg.degreeOut(test1.OKSolution(AOB, targS, torpFireS)));
		}
		System.out.println("*********************torpFireS sweep: \n");		
		for(torpFireS = -1; torpFireS < 80; torpFireS++){
			targS = 12;
			AOB = 80;
			System.out.println("AOB: " + AOB + " targSpeed: " + targS +" TorpSpeed: " +torpFireS);
			System.out.println("Scope Bearing: " + toDeg.degreeOut(test1.OKSolution(AOB, targS, torpFireS)));
			
		}			
		
	}
	
}

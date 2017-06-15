package coreLogic;

import xmlParser.PrepShipData;

import java.lang.invoke.MethodHandles;

import org.apache.logging.log4j.*;

/**
 * @author fps 
 * Main class for running different components.
 */
public class MethodTest {

	final static Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

	public static void main(String[] args) {
		oKaneTest();
		nomoTest();
		aspectAOBTest();
	}

	public static void nomoTest() {
		Nomo testNom = new Nomo();
		// S(kn)D(m)T(s)
		logger.info(testNom.nomo(0, 8000, 1500));
		logger.info(testNom.nomo(5.144, 0, 1500));
		logger.info(testNom.nomo(5.144, 8000, 0));
	}

	public static void aspectAOBTest() {
		OutFormat toDeg = new OutFormat();
		AspectAOB testAOB = new AspectAOB();
		PrepShipData target = new PrepShipData();
		Ship shipAOB = target.getShipByID(30);

		int estAOB = 35;
		double mastObs = 8.5;
		double lenObs = 35;

		logger.info(shipAOB.toString());
		logger.info(toDeg.degreeOut(testAOB.aspectAOB(95, shipAOB, 2.7, 9.8)));
		logger.info(toDeg.degreeOut(testAOB.aspectAOB(310, shipAOB, 7.1, 4 * 8.1)));
	}

	public static void oKaneTest() {

		OKane test1 = new OKane();
		// constructs a torpedo. INPUTS: name, slow/
		//TODO:med/fast speed (kn),
		// rangeS, TODO: rangeMedium, rangeF (m)
		
		Torpedo mk10 = new Torpedo("Mk. 10", 36, -1, -1, 3200, -1);
		Torpedo mk14 = new Torpedo("Mk. 14", 31, -1, 46, 8200, 4100);
		Torpedo mk16 = new Torpedo("Mk. 16", 46, -1, -1, 12500, -1);

		
		// Okane parameters
		int AOB = 95;
		double targS = 6;
		double torpFireS = 46;

		// 360AOB, 0-60 targS,
		logger.info("*********************AOBSweep: \n");
		for (AOB = 0; AOB < 360; AOB++) {
			torpFireS = 46;
			targS = 12;
			logger.info("AOB: " + AOB + " targSpeed: " + targS + " TorpSpeed: " + torpFireS);
			logger.info("Scope Bearing: " + OutFormat.degreeOut(test1.OKSolution(AOB, targS, torpFireS)));
		}
		logger.info("*********************targS sweep: \n");
		for (targS = -1; targS < 31; targS++) {
			torpFireS = 46;
			AOB = 275;
			logger.info("AOB: " + AOB + " targSpeed: " + targS + " TorpSpeed: " + torpFireS);
			logger.info("Scope Bearing: " + OutFormat.degreeOut(test1.OKSolution(AOB, targS, torpFireS)));
		}
		logger.info("*********************torpFireS sweep: \n");
		for (torpFireS = -1; torpFireS < 80; torpFireS++) {
			targS = 12;
			AOB = 80;
			logger.info("AOB: " + AOB + " targSpeed: " + targS + " TorpSpeed: " + torpFireS);
			logger.info("Scope Bearing: " + OutFormat.degreeOut(test1.OKSolution(AOB, targS, torpFireS)));

		}

	}

}

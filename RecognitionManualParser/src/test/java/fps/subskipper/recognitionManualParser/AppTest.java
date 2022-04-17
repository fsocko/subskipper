package fps.subskipper.recognitionManualParser;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    /*
    @Test
    public void testXYZ() {
        final File expected = new File("xyz.txt");
        final File output = folder.newFile("xyz.txt");
        TestClass.xyz(output);
        Assert.assertEquals(FileUtils.readLines(expected), FileUtils.readLines(output));
    }
    */

    private final String testResourcesPath = (System.getProperty("user.dir") + "\\src\\test\\resources\\");

    @Test
    public void shouldPublishShortRecogManual() throws IOException {
        App app = new App();
        app.publishShortRecognitionManual(testResourcesPath, true, true);

        assertNotNull(new File(testResourcesPath) + "\\testFile.html");

    }
}



/*

package coreLogic;
import xmlParser.PrepShipData;

public class MethodTest {

	public static void main(String[] args) 
	{
		oKaneTest();
	}
	
	public static void nomoTest(){
		Nomo testNom = new Nomo();
		 //S(kn)D(m)T(s)
		System.out.println(testNom.nomo(0, 8000, 1500));
		System.out.println(testNom.nomo(5.144, 0, 1500));
		System.out.println(testNom.nomo(5.144, 8000, 0));
	}
	
	public static void aspectAOBTest(){
		OutFormat toDeg = new OutFormat();
		AspectAOB testAOB = new AspectAOB();
		PrepShipData target = new PrepShipData();
		Ship shipAOB = target.getShipByID(30);
		
		int estAOB = 35;
		double mastObs = 8.5;
		double lenObs = 35;
		
		System.out.println(shipAOB.toString());
		System.out.println(toDeg.degreeOut(testAOB.aspectAOB(95, shipAOB, 2.7, 9.8)));
		System.out.println(toDeg.degreeOut(testAOB.aspectAOB(310, shipAOB, 7.1, 4*8.1)));
	}
	
	
	public static void oKaneTest(){
		
		OKaneSolutionCalculator test1 = new OKaneSolutionCalculator();
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
		for(targS = -1; targS < 31; targS++)
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


*/
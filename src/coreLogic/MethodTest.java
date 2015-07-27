package coreLogic;

public class MethodTest {

	public static void main(String[] args) 
	{
		OKane test1 = new OKane();
		//constructs a torpedo. INPUTS: name, slow/fast speed (kn), rangeS, rangeF (m)
		Torpedo mk10 = new Torpedo("Mk. 10", 36, -1, 3200, -1);
		Torpedo mk14 = new Torpedo("Mk. 14", 31, 46, 8200, 4100);
		Torpedo mk16 = new Torpedo("Mk. 16", 46, -1, 12500, -1);
		
		int AOB = 0;
		double targS = 6;
		char selSpeed = 'f';
		Torpedo fireTorp = mk14; 
		//oKSolution(int AOB, double targS, char selSpeed, Torpedo fireTorp)
		System.out.println("AOB: " + AOB + " targSpeed: " + targS +" TorpSpeed: " +selSpeed + fireTorp.getName());
		System.out.println("Scope Bearing: " + test1.oKSolution(AOB, targS, selSpeed, fireTorp));
		
		OutFormat toDeg = new OutFormat();
		
		for(int i = 0; i<361; i++){
		System.out.println(toDeg.degreeOut(i));
		}
	
	}
	

}

package coreLogic;

public class Torpedo {

	private int ID;
	private String name; // torp Name
	private double speedS; // slow speed
	private double speedM; // med speed
	private double speedF; // fast speed
	private double rangeS; // torp range, slow
	private double rangeM; // torp range, medium
	private double rangeF; // torp range, fast
	private double impactT; // time to impact on this solution

	// TODO: location:
	// bearing
	// distance
	// time away

	// TODO: get speed as L/M/H, determine speed value based on torpedo type and
	// its properties.

	/**
	 * constructs a torpedo.
	 * 
	 * @param name
	 *            torp Name
	 * 
	 * @param speedS
	 *            slow speed
	 * 
	 * @param speedF
	 *            fast speed
	 * 
	 * @param rangeS
	 *            range slow
	 * 
	 * @param speedM
	 *            torp range, med
	 * 
	 * @param rangeS
	 *            torp range, slow.
	 * 
	 * @param rangeF
	 *            torp range, fast.
	 * 
	 */

	// FIXME: add location data
	public Torpedo(String name, double speedS, double speedF, double speedM, double rangeS, double rangeF) {
		this.ID = 001;
		this.name = name;
		this.speedS = speedS;
		this.speedF = speedF;
		this.rangeS = rangeS;
		this.rangeF = rangeF;
		this.impactT = 0;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public double getImpactT() {
		return impactT;
	}

	public void setImpactT(double impactT) {
		this.impactT = impactT;
	}

	public String getName() {
		return name;
	}

	public double getSpeedS() {
		return speedS;
	}

	public double getSpeedF() {
		return speedF;
	}

	public double getRangeS() {
		return rangeS;
	}

	public double getRangeF() {
		return rangeF;
	}

}

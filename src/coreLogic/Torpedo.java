package coreLogic;

import java.lang.invoke.MethodHandles;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class Torpedo.
 */
public class Torpedo {

	final static Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
	
	/** The id. */
	private int ID;
	
	/** The name. */
	private String name; 
	
	/** The speed Slow. */
	private double speedS;
	
	/** The speed Medium. */
	private double speedM;
	
	/** The speed Fast. */
	private double speedF; // fast speed
	
	/** The range for a slow torpedo setting. */
	private double rangeS;
	
	/** The range for a Med torpedo setting */
	private double rangeM;
	
	/** The range for a fast torpedo setting */
	private double rangeF; 
	
	/** The impact Time for this solution / torpedo: 
	 * set by Stopwatch, so mostlikely nano seconds. TODO:. */
	private double impactT; 

	// TODO: location:
	// bearing
	// distance
	// time away

	// TODO: get speed as L/M/H, determine speed value based on torpedo type and
	// its properties.

	/**
	 * constructs a torpedo.
	 *
	 * @param name            	torp Name
	 * @param speedS            slow speed
	 * @param speedF            fast speed
	 * @param speedM            torp range, med
	 * @param rangeS            torp range, slow.
	 * @param rangeF            torp range, fast.
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

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Sets the id.
	 *
	 * @param iD the new id
	 */
	public void setID(int iD) {
		ID = iD;
	}

	/**
	 * Gets the impact T.
	 *
	 * @return the impact T
	 */
	public double getImpactT() {
		return impactT;
	}

	/**
	 * Sets the impact T.
	 *
	 * @param impactT the new impact T
	 */
	public void setImpactT(double impactT) {
		this.impactT = impactT;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the speed S.
	 *
	 * @return the speed S
	 */
	public double getSpeedS() {
		return speedS;
	}

	/**
	 * Gets the speed F.
	 *
	 * @return the speed F
	 */
	public double getSpeedF() {
		return speedF;
	}

	/**
	 * Gets the range S.
	 *
	 * @return the range S
	 */
	public double getRangeS() {
		return rangeS;
	}

	/**
	 * Gets the range F.
	 *
	 * @return the range F
	 */
	public double getRangeF() {
		return rangeF;
	}

}

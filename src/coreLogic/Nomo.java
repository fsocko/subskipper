package coreLogic;

import java.lang.invoke.MethodHandles;

import org.apache.logging.log4j.*;

/**
 * The Class Nomo.
 */
public class Nomo {

	/** The logger. */
	final static Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

	/**
	 * Nomo.
	 * Nomograph Logic: Inputs: any two of: speed , distance, time
	 * Returns a string which contains the missing value. Missing value
	 * must be a double, size 0.
	 * 
	 * NOTE: as long as distance and speed corespond to speed: e.g. speed = m/s, d=m, t=s
	 * then units do not matter. Really though, it should probably be m/s.
	 *
	 * @param speed the speed - units in distance / time
	 * @param distance the distance 
	 * @param time the time
	 * @return return the missing value: double speed, dist or time.
	 */
	public String nomo(double speed, double distance, double time) {

		// FIXME: validate if valid numbers and not null.

		OutFormat nomOut = new OutFormat();

		if (speed == 0) {
			speed = distance / time;
		} else if (distance == 0) {
			distance = speed * time;
		} else if (time == 0) {
			time = distance / speed;
		}

		String nomoOut = nomOut.msToKnot(speed) + "\nDistance: " 
		+ nomOut.addUnit(distance, "m") + "\nTime: "
		+ nomOut.hourOut(time);

		logger.info("Speed:{}", nomoOut);
		return (nomoOut);
	}

}

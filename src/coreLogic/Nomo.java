package coreLogic;

import org.apache.logging.log4j.*;

public class Nomo {

	final Logger logger = LogManager.getLogger(this.getClass());

	/**
	 * @param s
	 * @param d
	 * @param t
	 * @return Nomograph Logic: Inputs: speed (m/s), distance(m), time(s)
	 *         Returns a string which contains the missing value. Missing value
	 *         must be a double, size 0.
	 */
	public String nomo(double s, double d, double t) {

		// FIXME: validate if valid numbers and not null.

		OutFormat nomOut = new OutFormat();

		double speed = s;
		double dist = d;
		double time = t;

		if (s == 0) {
			speed = d / t;
		} else if (d == 0) {
			dist = s * t;
		} else if (t == 0) {
			time = d / s;
		}

		String nomoOut = nomOut.msToKnot(speed) + "\nDistance: " + nomOut.addUnit(dist, "m") + "\nTime: "
				+ nomOut.hourOut(time);

		logger.info("Speed:{}", nomoOut);
		return (nomoOut);
	}

}

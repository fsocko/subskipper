package coreLogic;

import java.lang.invoke.MethodHandles;
import java.text.*;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.*;

// TODO: Auto-generated Javadoc
/**
 * The Class OutFormat.
 *
 * @author fps Method for formatting output using a formatter to a String.
 *         String aString = String.format("%03d", "0.00123332");
 */
public class OutFormat {

	final static Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

	/**
	 * Degree out.
	 * 
	 * @param degIn
	 *            the deg in, unformatted.
	 * @return formatted degree string.
	 */
	public static String degreeOut(double degIn) {
		return addUnit(degIn, "deg");
	}

	/**
	 * convert from metre per second to knot.
	 *
	 * @param ms
	 *            the speed in ms
	 * @return the string
	 */
	@Deprecated
	public static String msToKnot(double ms) {
		double kt = ms * 1.94384449;
		String out = addUnit(ms, "kt");
		return out;
	}

	/**
	 * M to ft. convert from m to ft -- Deprecated. Use UnitConversions.java,
	 * then addUnit() as needed.
	 *
	 * @param m
	 *            the value in metres
	 * @return the string
	 */
	@Deprecated
	public static String mToFt(double m) {
		double ft = m * 3.28084;
		String out = addUnit(ft, "ft");
		return out;
	}

	/**
	 * Adds the unit. UnitConversions.java, then addUnit() as needed.
	 *
	 * @param value the value
	 * @param unit the unit
	 * @return the string
	 */
	public static String addUnit(double value, String unit) {

		String u = unit;
		String v = Double.toString(value);
		
		if ("deg".equals(unit)) {
			v=degFormat(value);
			unit = "\u00b0";
		}

		return v + u;
	}

	/**
	 * Two DP.
	 *
	 * @param twoDP
	 *            the value formatted to two DP string
	 * @return the string
	 */
	// Two figures after decimal
	public static String twoDP(double twoDP) {

		DecimalFormat formatter = new DecimalFormat("0.00");
		String out = formatter.format(twoDP);
		return out;
	}

	/**
	 * Four DP. Four figures after decimal
	 *
	 * @param fourDP
	 *            the four DP
	 * @return the string
	 */
	public static String fourDP(double fourDP) { // TODO: arbitrary DP would be
		// nice.
		DecimalFormat formatter = new DecimalFormat("0.0000");
		String out = formatter.format(fourDP);
		return out;
	}
	
	/**
	 * Deg format.
	 *
	 * @param degIn the deg in
	 * @return the string
	 */
	public static String degFormat(double degIn) {
		DecimalFormat formatter = new DecimalFormat("000");
		String out = formatter.format(degIn);
		return out;
	}
	

	/**
	 * Hour out.
	 *
	 * @param msIn
	 *            the milliseconds in
	 * @return the string
	 */
	public static String hourOut(long millis) {
		
			return String.format("%02d:%02d:%02d", 
				TimeUnit.MILLISECONDS.toHours(millis),
				TimeUnit.MILLISECONDS.toMinutes(millis) -  
				TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
				TimeUnit.MILLISECONDS.toSeconds(millis) - 
				TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));   
	}
	

	/**
	 * The Class UnitConversions.
	 */
	private class UnitConversions {

		// Speed: Meter per second to knot
		/**
		 * Ms kn.
		 *
		 * @param ms the ms
		 * @return the double
		 */
		// 1 metre per second = 1.94384449 knot
		public double msKn(double ms) {
			double knot = ms * 1.94384449;
			return knot;
		}

		// Knot to Kph
		/**
		 * Kn kmh.
		 *
		 * @param knot the knot
		 * @return the double
		 */
		// 1knots= 1.8520000kph
		public double knKmh(double knot) {
			double kph = knot * 1.8520000;
			return kph;
		}

		// m per Minute to knot
		/**
		 * M M kn.
		 *
		 * @param mM the m M
		 * @return the double
		 */
		// 1 metre per minute = 0.0323974082 knot
		public double mMKn(double mM) {
			double kn = mM * 0.0323974082;
			return kn;
		}
	}

}

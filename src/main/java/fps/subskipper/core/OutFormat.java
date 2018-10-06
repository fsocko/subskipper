package fps.subskipper.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;
import static fps.subskipper.util.Constants.*;

// TODO: Auto-generated Javadoc

/**
 * The Class OutFormat.
 *
 * @author fps Method for formatting output using a formatter to a String.
 */
public class OutFormat {

    final static Logger logger = LogManager.getLogger(OutFormat.class);

    /**
     * Appends arbitrary unit to value.
     *
     * @param value the value
     * @param unit  the unit
     * @return the string
     */
    public static String addUnit(double value, String unit) {

        String u = unit;
        String v = Double.toString(value);

        if ("deg".equals(unit)) {
            v = formatNumberToDegree(value);
            u = DEGREE_SYMBOL;
        }
        return v + u;
    }

    private static String arbitraryDecimalPlaces(double input, int decimalPlaces) {
        String format = ("%." + decimalPlaces + "f");
        DecimalFormat formatter = new DecimalFormat(format);
        return formatter.format(input);
    }

    public static String twoDP(double input) {
        return arbitraryDecimalPlaces(input, 2);
    }

    public static String fourDP(double input) {
        return arbitraryDecimalPlaces(input, 4);
    }

    public static String formatNumberToDegree(double degIn) {
        DecimalFormat formatter = new DecimalFormat("000");
        degIn = degIn % 360; //TODO perhaps throw ex to check for invalid deg?
        return formatter.format(degIn);
    }


    /**
     * Hour out.
     *
     * @param millis the milliseconds in
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


    private class UnitConversions {

        public double metrePerSecondToKnot(double ms) {
            return ms * KNOTS_FOR_EVERY_METRE_PER_SECOND;
        }

        public double knotToKilometrePerHour(double knot) {
            return knot * KNOTS_FOR_EVERY_KILOMETRE_PER_HOUR;
        }

        public double meterPerMinuteToKnot(double mM) {
            return mM * METRES_PER_MINUTE_FOR_EVERY_KNOT;
        }
    }
}

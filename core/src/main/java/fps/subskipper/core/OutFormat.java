package fps.subskipper.core;

import lombok.extern.slf4j.Slf4j;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

import static fps.subskipper.util.Constants.*;

// TODO: Auto-generated Javadoc

/**
 * The Class OutFormat.
 *
 * @author fps Method for formatting output using a formatter to a String.
 */
@Slf4j
public class OutFormat {

    private Object value;
    private String unit;
    private String formattedValue;
    private String formattedStringValue;

    private double conversionFactor;

    private OutFormat(Builder builder) {
        this.value = builder.value;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Object value;

        private boolean formatToDegrees = false;

        private boolean formatToHours = false;

        private int decimalPlaces;
        private String unit;
        private String formattedValue;
        private String toHoursString;
        private String formattedStringValue;
        private double conversionFactor = 1.0d;
        private StringBuilder formattedOutputString;


        private Builder() {
        }

        public Builder value(double value) {
            this.value = value;
            return this;
        }

        public Builder value(String value) {
            try {
                this.value = Double.parseDouble(value.trim());
            } catch(NumberFormatException nfe){
                this.value = value;
            }
            return this;
        }

        public Builder unit(String unit) {
            this.unit = unit;
            return this;
        }

        public Builder toDegrees() {
            this.formatToDegrees = true;
            return this;
        }

        public Builder zeroDP() {
            this.decimalPlaces = 0;
            return this;
        }

        public Builder twoDP() {
            this.decimalPlaces = 2;
            return this;
        }

        public Builder fourDP() {
            this.decimalPlaces = 4;
            return this;
        }

        public Builder dp(int decimalPlaces) {
            this.decimalPlaces = decimalPlaces;
            return this;
        }

        /**
         * Hour out.
         *
         * @param millis the milliseconds in
         * @return the string
         */
        public Builder toHours(long millis) {

            this.formatToHours = true;

            this.toHoursString = String.format("%02d:%02d:%02d",
                    TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis) -
                            TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
            return this;
        }



        private Builder metrePerSecondToKnot(double ms) {
            this.conversionFactor = KNOTS_FOR_EVERY_METRE_PER_SECOND;
            return this;
        }

        private Builder knotToKilometrePerHour(double knot)
        {
            this.conversionFactor = KNOTS_FOR_EVERY_KILOMETRE_PER_HOUR;
            return this;
        }

        private Builder meterPerMinuteToKnot(double mM) {
            this.conversionFactor = METRES_PER_MINUTE_FOR_EVERY_KNOT;
            return this;
        }

        public String build() {  //TODO: rename to format

            if(formatToDegrees){
                return formatValueToDegree((double)this.value);
            }
            else if (formatToHours){
                return formatValueToHours((long)value);
            }
            else {
                if(this.conversionFactor != 1){
                    this.value = (double)value * conversionFactor;
                }

                this.formattedOutputString = new StringBuilder(arbitraryDecimalPlaces((double) value, this.decimalPlaces));

                if(this.unit != null ){
                    this.formattedOutputString.append(" " + this.unit);
                }
            }

            return this.formattedOutputString.toString();
        }

        private String arbitraryDecimalPlaces(double input, int decimalPlaces) {
            String format = ("%." + decimalPlaces + "f");
            return String.format(format, input);
        }

        public String formatValueToDegree(double value) {
            double degIn = (double)value;

            if(degIn < 0 ){
                degIn = Math.abs(360 + degIn);
            }

            DecimalFormat formatter = new DecimalFormat("000");
            degIn = degIn % 360; //TODO perhaps throw ex to check for invalid deg?
            return formatter.format(degIn) + UNIT_DEGREE;
        }

        /**
         * Hour out.
         *
         * @param millis the milliseconds in
         * @return the string
         */
        public String formatValueToHours(long millis) {

            return String.format("%02d:%02d:%02d",
                    TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis) -
                            TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
        }


    }






    /**
     * Appends arbitrary unit to value.
     *
     * @param value the value
     * @param unit  the unit
     * @return the string
     */
//    private String addUnit(double value, String unit) {
//
//        String u = unit;
//        String v = Double.toString(value);
//
//        if ("deg".equals(unit)) {
//            v = formatNumberToDegree(value);
//            u = UNIT_DEGREE;
//        }
//        return v + u;
//    }

    //If number is already formatted, just append the unit.
    private String addUnit(String value, String unit) {
        return value + unit;
    }

    private String arbitraryDecimalPlaces(double input, int decimalPlaces) {
        String format = ("%." + decimalPlaces + "f");
        return String.format(format, input);
    }

    public String zeroDP(double input) {
        return arbitraryDecimalPlaces(input, 0);
    }

    public String twoDP(double input) {
        return arbitraryDecimalPlaces(input, 2);
    }

    public String fourDP(double input) {
        return arbitraryDecimalPlaces(input, 4);
    }

    public String formatValueToDegree(double degIn) {
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
    public String formatValueToHours(long millis) {

        return String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                TimeUnit.MILLISECONDS.toSeconds(millis) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
    }



        private double metrePerSecondToKnot(double ms) {
            return ms * KNOTS_FOR_EVERY_METRE_PER_SECOND;
        }

        private double knotToKilometrePerHour(double knot) {
            return knot * KNOTS_FOR_EVERY_KILOMETRE_PER_HOUR;
        }

        private double meterPerMinuteToKnot(double mM) {
            return mM * METRES_PER_MINUTE_FOR_EVERY_KNOT;
        }

    }

package fps.subskipper.core;

import lombok.extern.slf4j.Slf4j;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
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

    public static Builder builder(double value) {
        Builder builder = new Builder();
        builder.value = value;
        return builder;

    }

    public static class Builder {
        private Object value;

        private boolean formatToDegrees = false;

        private boolean formatToHours = false;

        private int decimalPlaces;
        private String unit;
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
                double parsedValue = Double.parseDouble(value.trim());
                this.value = parsedValue;
            } catch (NumberFormatException nfe) {
                this.value = value.trim();
            }
            return this;
        }

        public Builder unit(String unit) {
            this.unit = unit;
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

        public Builder addUnit(String unit) {
            this.unit = unit;
            return this;
        }

        public Builder toHours() {
            this.formatToHours = true;
            return this;
        }
        public Builder toDegrees() {
            this.formatToDegrees = true;
            return this;
        }

        public Builder metrePerSecondToKnot() {
            this.conversionFactor = KNOTS_FOR_EVERY_METRE_PER_SECOND;
            return this;
        }

        public Builder knotToKilometrePerHour() {
            this.conversionFactor = KNOTS_FOR_EVERY_KILOMETRE_PER_HOUR;
            return this;
        }

        public Builder meterPerMinuteToKnot() {
            this.conversionFactor = METRES_PER_MINUTE_FOR_EVERY_KNOT;
            return this;
        }

        public Builder metresToFeet() {
            this.conversionFactor = FEET_FOR_EVERY_METRE;
            return this;
        }

        public Builder addMultiplier(double multiplier) {
            this.conversionFactor = multiplier;
            return this;
        }

        public String build() {

            //If String, and cannot be parsed into a double, only allow some methods.
            if (value != null && value instanceof String) {
                if (this.unit != null) {
                    return addUnitToStringValue((String) value);
                }
            }

            if (formatToDegrees || (this.unit != null && this.unit.equals(UNIT_DEGREE))) {
                return formatValueToDegree((double) this.value);

            } else if (formatToHours) {
                return formatValueToHours((long) value);

            } else {
                if (this.conversionFactor != 1) {
                    this.value = (double) value * conversionFactor;
                }

                this.formattedOutputString = new StringBuilder(arbitraryDecimalPlaces((double) value, this.decimalPlaces));

                if (this.unit != null) {
                    this.formattedOutputString.append(" " + this.unit);
                }
            }

            return this.formattedOutputString.toString();
        }

        private String arbitraryDecimalPlaces(double input, int decimalPlaces) {
            String format = ("%." + decimalPlaces + "f");
            return String.format(format, input);
        }

        private String formatValueToDegree(double value) {
            double degIn = (double) value;
            if (degIn < 0) {
                degIn = Math.abs(360 + degIn);
            }
            DecimalFormat formatter = new DecimalFormat("000");
            degIn = degIn % 360; //TODO perhaps throw ex to check for invalid deg?
            return formatter.format(degIn) + UNIT_DEGREE;
        }

        public String formatValueToHours(long millis) {
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss");
            Instant instant = Instant.ofEpochMilli(millis);
            ZoneId zoneId = ZoneId.systemDefault();
            LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
            return localDateTime.format(timeFormatter).toString();
        }

        private String addUnitToStringValue(String value) {
            return value.trim() + " " + this.unit;
        }
    }

}

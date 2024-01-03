//Ship Object. Parse from file to this object, then write to XML file for use
//by SubSkipper Android.
package fps.subskipper.core;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

import static fps.subskipper.util.Constants.FEET_FOR_EVERY_METRE;


@Slf4j
@Getter
@Setter
@XmlRootElement(name = "ship")
public class Ship implements Comparable<Ship> {

    private int id;
    private final String nation;
    private final String name;
    private final int type;
    private final String typeName;
    private final String imagePath;
    private final double maxSpeed;
    private final double length;
    private final double width;
    private final double mast;
    private final double draft;
    private final double displacement;
    private final double refAspect;
    //TODO: Angle solver data


    public Ship(String name, int type, String typeName, String imagePath, double maxSpeed, double length, double width,
                double mast, double draft, double displacement) {

        this.id = new AtomicInteger().addAndGet(1);
        this.nation = "none";
        this.name = name;
        this.type = type;
        this.typeName = typeName;
        this.imagePath = imagePath;
        this.maxSpeed = maxSpeed;
        this.length = length;
        this.width = width;
        this.mast = mast;
        this.draft = draft;
        this.displacement = displacement;
        this.refAspect = length / mast;
    }
    public double getImperialLength() {
        return length * FEET_FOR_EVERY_METRE;
    } //Imperial conversions
    public double getImperialWidth() {
        return width * FEET_FOR_EVERY_METRE;
    } //Imperial conversions
    public double getImperialMast() {
        return mast * FEET_FOR_EVERY_METRE;
    } //Imperial conversions
    public double getImperialDraft() {
        return draft * FEET_FOR_EVERY_METRE;
    } //Imperial conversions
    public double getReferenceAspectRatio() {
        return refAspect;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "id=" + id +
                ", nation='" + nation + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", typeName='" + typeName + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", maxSpeed=" + maxSpeed +
                ", length=" + length +
                ", width=" + width +
                ", mast=" + mast +
                ", draft=" + draft +
                ", displacement=" + displacement +
                ", refAspect=" + refAspect +
                '}';
    }

    /*
     * Comparators for sorting with collections.sort() by type, descending.
     */
    public int compareTo(Ship ship) {
        if (ship.getType() > this.getType()) {
            return 1;
        } else if (ship.getType() < this.getType()) {
            return -1;
        } else if (ship.getType() == this.getType()) {
            return 0;
        } else {
            log.error("Unable to compare\n%s\nto:\n%s by type. Returning 0.", this.toString(), ship.toString());
            return 0; //TODO: Throw Exception?
        }
    }

    public static Comparator<Ship> sTypeComparatorDescending = new Comparator<Ship>() {
        public int compare(Ship s1, Ship s2) {
            int s1T = s1.getType();
            int s2T = s2.getType();
            return s2T - s1T;
        }
    };

    public static Comparator<Ship> sNameComparatorDescending = new Comparator<Ship>() {
        public int compare(Ship s1, Ship s2) {
            String s1Name = s1.getName().toUpperCase();
            String s2Name = s2.getName().toUpperCase();
            return s1Name.compareTo(s2Name);
        }
    };
}

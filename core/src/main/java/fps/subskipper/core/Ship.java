//Ship Object. Parse from file to this object, then write to XML file for use
//by SubSkipper Android.
package fps.subskipper.core;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

import static fps.subskipper.util.Constants.FEET_FOR_EVERY_METRE;


@Slf4j
@Getter
@XmlRootElement(name = "ship")
public class Ship implements Comparable<Ship> {

    static final AtomicInteger idGen = new AtomicInteger(1);

    private int id;
    private String nation;
    private String name;
    private int type;
    private String typeName;
    private String image;
    private double maxSpeed;
    private double length;
    private double width;
    private double mast;
    private double draft;
    private double displacement;
    private double refAspect;
    //TODO: Angle solver data



    public Ship(String name, int type, String typeName, String image, double maxSpeed, double length, double width,
                double mast, double draft, double displacement) {

        //this.id = idGen.getAndIncrement();
        this.id = 0;
        this.nation = "none";
        this.name = name;
        this.type = type;
        this.typeName = typeName;
        this.image = image;
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
                ", imagePath='" + image + '\'' +
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
            throw new RuntimeException("Unable to compare by type:" + this.getType() +" -> "+ ship.getType());
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

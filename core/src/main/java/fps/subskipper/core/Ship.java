//Ship Object. Parse from file to this object, then write to XML file for use
//by SubSkipper Android.
package fps.subskipper.core;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

import static fps.subskipper.util.Constants.FEET_FOR_EVERY_METRE;


@Slf4j
@Getter
@XmlRootElement(name = "ship")
public class Ship implements Comparable<Ship> {

    private int id;
    private String nation;
    private String name;
    private int type;
    private String typeName;
    private String imagePath;
    private double maxSpeed;
    private double length;
    private double width;
    private double mast;
    private double draft;
    private double displacement;
    private double refAspect;
    //TODO: Angle solver data

    public Ship(String... params) {
        this.id = new AtomicInteger().addAndGet(1);
        this.nation = "none";
        this.name = "";
        this.type = -1;
        this.typeName = "";
        this.imagePath = "";
        this.maxSpeed = -1;
        this.length = -1;
        this.width = -1;
        this.mast = -1;
        this.draft = -1;
        this.displacement = -1;
        this.refAspect = length / mast;

        if (params.length >= 1) {
            this.name = params[0];
        }
        if (params.length >= 2) {
            this.type = Integer.parseInt(params[1]);
        }
        if (params.length >= 3) {
            this.typeName = params[2];
        }
        if (params.length >= 4) {
            this.imagePath = params[3];
        }
        if (params.length >= 5) {
            this.maxSpeed = Double.parseDouble(params[4]);
        }
        if (params.length >= 6) {
            this.length = Double.parseDouble(params[5]);
        }
        if (params.length >= 7) {
            this.width = Double.parseDouble(params[6]);
        }
        if (params.length >= 8) {
            this.mast = Double.parseDouble(params[7]);
        }
        if (params.length >= 9) {
            this.draft = Double.parseDouble(params[8]);
        }
        if (params.length >= 10) {
            this.displacement = Double.parseDouble(params[9]);
        }
        this.refAspect = length / mast;
    }

    public Ship() {
        this.id = new AtomicInteger().addAndGet(1);
        this.nation = "none";
        this.name = "";
        this.type = -1;
        this.typeName = "";
        this.imagePath = "";
        this.maxSpeed = -1;
        this.length = -1;
        this.width = -1;
        this.mast = -1;
        this.draft = -1;
        this.displacement = -1;
        this.refAspect = length / mast;
    }

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

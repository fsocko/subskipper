//Ship Object. Parse from file to this object, then write to XML file for use
//by SubSkipper Android.
package fps.subskipper.core;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.lang.invoke.MethodHandles;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

import static fps.subskipper.core.util.Constants.FEET_FOR_EVERY_METRE;

@Slf4j
@XmlRootElement(name = "ship")
@XmlAccessorType(XmlAccessType.FIELD)
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
    //TODO: Angle solver data goes here in new object.. e.g. AOB, Target Bearing etc.


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

    public int getId() {
        return id;
    }

    public String getNation() {
        return nation;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public double getLength() {
        return length;
    }

    public double getImperialLength() {
        return length * FEET_FOR_EVERY_METRE;
    } //Imperial conversions

    public double getWidth() {
        return width;
    }

    public double getImperialWidth() {
        return width * FEET_FOR_EVERY_METRE;
    } //Imperial conversions

    public double getMast() {
        return mast;
    }

    public double getImperialMast() {
        return mast * FEET_FOR_EVERY_METRE;
    } //Imperial conversions

    public double getDraft() {
        return draft;
    }

    public double getImperialDraft() {
        return draft * FEET_FOR_EVERY_METRE;
    } //Imperial conversions

    public double getDisplacement() {
        return displacement;
    }

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
            return 0;
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

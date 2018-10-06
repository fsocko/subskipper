//Ship Object. Parse from file to this object, then write to XML file for use
//by SubSkipper Android.
package fps.subskipper.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.lang.invoke.MethodHandles;
import java.util.Comparator;


@XmlRootElement(name = "ship")
@XmlAccessorType(XmlAccessType.FIELD)

public class Ship implements Comparable<Ship> {

    final static Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());


    private int Id;
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
    private double disp;
    private double refAspect;
    //Angle solver data goes here:
    private double course;
    private double speed;


    //TODO: private double time spotted.
    //private int speed
    //private int / string nationality?
    private double tgtBearing;
    private double aob;

    public Ship(String name, int type, String typeName, String imagePath, double maxSpeed, double length, double width,
                double mast, double draft, double disp, double course, double speed, double tgtBearing, double aob) {
        super();
        this.Id = 0;
        this.nation = "none"; //TODO Once I figure out how nations are assigned in recog.Manual --
        this.name = name;
        this.type = type;
        this.typeName = typeName;
        this.imagePath = imagePath;
        this.maxSpeed = maxSpeed;
        this.length = length;
        this.width = width;
        this.mast = mast;
        this.draft = draft;
        this.disp = disp;
        this.refAspect = length / mast;
        this.course = course;
        this.speed = speed;
        this.tgtBearing = tgtBearing;
        this.aob = aob;
    }

    //This constructor is for building ships with data, but no course data.
    public Ship(String name, int type, String typeName, String imagePath, double maxSpeed, double length, double width,
                double mast, double draft, double disp) {
        super();
        this.Id = 0;
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
        this.disp = disp;
        this.refAspect = length / mast;
    }

    public Ship() {
        this.Id = -3;
        this.nation = "";
        this.name = "";
        this.type = -3;
        this.typeName = "";
        this.imagePath = "";
        this.maxSpeed = -3;
        this.length = -3;
        this.width = -3;
        this.mast = -3;
        this.draft = -3;
        this.disp = -3;
        this.refAspect = -3;
        this.course = -3;
        this.speed = -3;
        this.tgtBearing = -3;
        this.aob = -3;
    }

    public int getId() {
        return Id;
    }
    public void setId(int iD) {
        Id = iD;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getImagePath() {
        return imagePath;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = "\"" + imagePath + "\"";
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }
    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public double getLength() {
        return length;
    }
    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }
    public void setWidth(double width) {
        this.width = width;
    }

    public double getMast() {
        return mast;
    }
    public void setMast(double mast) {
        this.mast = mast;
    }

    public double getDraft() {
        return draft;
    }
    public void setDraft(double draft) {
        this.draft = draft;
    }

    public double getDisp() {
        return disp;
    }
    public void setDisp(double disp) {
        this.disp = disp;
    }

    public double getReferenceAspectRatio() {
        return refAspect;
    }
    public void setReferenceAspectRatio(double refAspect) {
        this.refAspect = refAspect;
    }

    @Override
    public String toString() {
        return "Ship [Id=" + Id + ", nation=" + nation + ", name=" + name + ", type=" + type + ", typeName="
                + typeName + ", imagePath=" + imagePath + ", maxSpeed=" + maxSpeed + ", length=" + length + ", width="
                + width + ", mast=" + mast + ", draft=" + draft + ", disp=" + disp + ", refAspect=" + refAspect
                + ", course=" + course + ", speed=" + speed + ", tgtBearing=" + tgtBearing + ", aob=" + aob + "]";
    }

    /*
     * Comparators for sorting with collections.sort() by type, descending.
     */
    public int compareTo(Ship ship) {
        if(ship.getType() > this.getType()){return 1;}
        else if(ship.getType() < this.getType()){return -1;}
        else if(ship.getType() == this.getType()){return 0;}
        else{
            logger.error("Unable to compare\n%s\nto:\n%s by type. Returning 0.", this.toString(), ship.toString());
            return 0;
        }
    }

    /**
     * The s type comparator, descending
     */
    public static Comparator<Ship> sTypeComparatorDescending = new Comparator<Ship>() {
        public int compare(Ship s1, Ship s2) {
            int s1T = s1.getType();
            int s2T = s2.getType();
            return s2T - s1T;
        }
    };
    /**
     * The s name comparator, descending
     */
    public static Comparator<Ship> sNameComparatorDescending = new Comparator<Ship>() {
        public int compare(Ship s1, Ship s2) {
            String s1Name = s1.getName().toUpperCase();
            String s2Name = s2.getName().toUpperCase();
            return s1Name.compareTo(s2Name);
        }
    };


    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public double getCourse() {
        return course;
    }

    public void setCourse(double course) {
        this.course = course;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getTgtBearing() {
        return tgtBearing;
    }

    public void setTgtBearing(double tgtBearing) {
        this.tgtBearing = tgtBearing;
    }

    public double getAob() {
        return aob;
    }

    public void setAob(double aob) {
        this.aob = aob;
    }
}

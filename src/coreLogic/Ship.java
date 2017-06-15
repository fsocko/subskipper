//Ship Object. Parse from file to this object, then write to XML file for use
//by SubSkipper Android.
package coreLogic;

import java.lang.invoke.MethodHandles;
import java.util.Comparator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


// TODO: Auto-generated Javadoc
/**
 * The Class Ship.
 */
@XmlRootElement(name = "ship")
@XmlAccessorType (XmlAccessType.FIELD)

public class Ship implements Comparable<Ship> {

	final static Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());
	
	/** The id. */
	private int ID;
	
	/** The nation. */
	private String nation;
	
	/** The name. */
	private String name;
	
	/** The type. */
	private int type;
	
	/** The type name. */
	private String typeName;
	
	/** The image path. */
	private String imagePath;
	
	/** The max speed. */
	private double maxSpeed;
	
	/** The length. */
	private double length;
	
	/** The width. */
	private double width;
	
	/** The mast. */
	private double mast;
	
	/** The draft. */
	private double draft;
	
	/** The disp. */
	private double disp;
	
	/** The ref aspect. */
	private double refAspect;
	
	/**
	 * Instantiates a new ship.
	 *
	 * @param name the name
	 * @param type the type
	 * @param typeName the type name
	 * @param imagePath the image path
	 * @param maxSpeed the max speed
	 * @param length the length
	 * @param width the width
	 * @param mast the mast
	 * @param draft the draft
	 * @param disp the disp
	 */
	public Ship(String name, int type, String typeName, String imagePath, double maxSpeed, double length, double width,
			double mast, double draft, double disp) {
		super();
		this.ID = 0;
		this.nation = "none"; //TODO Once I figure out how nations are assigned in recog.Manual --
							  //TODO: Assign manually? we can't really know from the recog anyway.
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

	/**
	 * Instantiates a new ship.
	 */
	public Ship() {
		this.ID = 0;
		this.nation = "";
		this.name = "";
		this.type = 0;
		this.typeName = "";
		this.imagePath = "";
		this.maxSpeed = 0;
		this.length = 0;
		this.width = 0;
		this.mast = 0;
		this.draft = 0;
		this.disp = 0;
		this.refAspect = 0;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getID() {
		return ID;
	}
	
	/**
	 * Sets the id.
	 *
	 * @param iD the new id
	 */
	public void setID(int iD) {
		ID = iD;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public int getType() {
		return type;
	}
	
	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * Gets the type name.
	 *
	 * @return the type name
	 */
	public String getTypeName() {
		return typeName;
	}
	
	/**
	 * Sets the type name.
	 *
	 * @param typeName the new type name
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * Gets the image path.
	 *
	 * @return the image path
	 */
	public String getImagePath() {
		return imagePath;
	}
	
	/**
	 * Sets the image path.
	 *
	 * @param imagePath the new image path
	 */
	public void setImagePath(String imagePath) {
		this.imagePath = "\"" + imagePath + "\"";
	}

	/**
	 * Gets the max speed.
	 *
	 * @return the max speed
	 */
	public double getMaxSpeed() {
		return maxSpeed;
	}
	
	/**
	 * Sets the max speed.
	 *
	 * @param maxSpeed the new max speed
	 */
	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	/**
	 * Gets the length.
	 *
	 * @return the length
	 */
	public double getLength() {
		return length;
	}
	
	/**
	 * Sets the length.
	 *
	 * @param length the new length
	 */
	public void setLength(double length) {
		this.length = length;
	}

	/**
	 * Gets the width.
	 *
	 * @return the width
	 */
	public double getWidth() {
		return width;
	}
	
	/**
	 * Sets the width.
	 *
	 * @param width the new width
	 */
	public void setWidth(double width) {
		this.width = width;
	}

	/**
	 * Gets the mast.
	 *
	 * @return the mast
	 */
	public double getMast() {
		return mast;
	}
	
	/**
	 * Sets the mast.
	 *
	 * @param mast the new mast
	 */
	public void setMast(double mast) {
		this.mast = mast;
	}

	/**
	 * Gets the draft.
	 *
	 * @return the draft
	 */
	public double getDraft() {
		return draft;
	}
	
	/**
	 * Sets the draft.
	 *
	 * @param draft the new draft
	 */
	public void setDraft(double draft) {
		this.draft = draft;
	}

	/**
	 * Gets the disp.
	 *
	 * @return the disp
	 */
	public double getDisp() {
		return disp;
	}
	
	/**
	 * Sets the disp.
	 *
	 * @param disp the new disp
	 */
	public void setDisp(double disp) {
		this.disp = disp;
	}

	/**
	 * Gets the ref aspect.
	 *
	 * @return the ref aspect
	 */
	public double getRefAspect() {
		return refAspect;
	}
	
	/**
	 * Sets the ref aspect.
	 *
	 * @param refAspect the new ref aspect
	 */
	public void setRefAspect(double refAspect) {
		this.refAspect = refAspect;
	}
	
	
	/* 
	 * Ship data to string.
	 */
	@Override
	public String toString() {
		return "Ship [ID=" + ID + ", name=" + name + ", type=" + type + ", typeName=" + typeName + ", imagePath="
				+ imagePath + ", maxSpeed=" + maxSpeed + ", length=" + length + ", width=" + width + ", mast=" + mast
				+ ", draft=" + draft + ", disp=" + disp + ", refAspect=" + refAspect + "]";
	}

	/* 
	 * Comparators for sorting with collections.sort()
	 */
	public int compareTo(Ship a) {
		int compareType = (a.getType());
		return compareType - this.getType();
	}
	
	/** The s type comparator, descending */
	//Type, Descending
	public static Comparator<Ship> sTypeCompD = new Comparator<Ship>() {
		public int compare(Ship s1, Ship s2){
			int s1T = s1.getType();
			int s2T = s2.getType();
			return s2T - s1T;
		}
	};
	
	/** The s name comp D. */
	//Name, Descending
	public static Comparator<Ship> sNameCompD = new Comparator<Ship>() {
		public int compare(Ship s1, Ship s2){
			String s1Name = s1.getName().toUpperCase();
			String s2Name = s2.getName().toUpperCase();
			return s1Name.compareTo(s2Name);
		}
	};
	
	/**
	 * Make imperial.
	 * Converts relevant measurements to "wrong" units - feet
	 *		FIXME: This should be refactored into unitConversions perhaps. 
	 *		Right now, the actual ship data is modified.
	 *		Also, magic numbers everywhere.
	 */
	public void makeImperial(){
		
		this.setLength(this.length * 3.28084);
		this.setWidth(this.width * 3.28084);
		this.setMast(this.mast * 3.28084);
		this.setDraft(this.draft * 3.28084);
	}
}

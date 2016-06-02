//Ship Object. Parse from file to this object, then write to XML file for use
//by SubSkipper Android.
package coreLogic;

import java.util.Comparator;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "ship")
@XmlAccessorType (XmlAccessType.FIELD)

public class Ship implements Comparable<Ship> {
	
	private int ID;
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
	
	public Ship(String name, int type, String typeName, String imagePath, double maxSpeed, double length, double width,
			double mast, double draft, double disp) {
		super();
		this.ID = 0;
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

	public int getID() {
		return ID;
	}
	
	public void setID(int iD) {
		ID = iD;
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

	public double getRefAspect() {
		return refAspect;
	}
	
	public void setRefAspect(double refAspect) {
		this.refAspect = refAspect;
	}
	
	
	@Override
	public String toString() {
		return "Ship [ID=" + ID + ", name=" + name + ", type=" + type + ", typeName=" + typeName + ", imagePath="
				+ imagePath + ", maxSpeed=" + maxSpeed + ", length=" + length + ", width=" + width + ", mast=" + mast
				+ ", draft=" + draft + ", disp=" + disp + ", refAspect=" + refAspect + "]";
	}

	//Comparators for sorting with collections.sort()
	public int compareTo(Ship a) {
		int compareType = (a.getType());
		return compareType - this.getType();
	}
	//Type, Descending
	public static Comparator<Ship> sTypeCompD = new Comparator<Ship>() {
		public int compare(Ship s1, Ship s2){
			int s1T = s1.getType();
			int s2T = s2.getType();
			return s2T - s1T;
		}
	};
	
	//Name, Descending
	public static Comparator<Ship> sNameCompD = new Comparator<Ship>() {
		public int compare(Ship s1, Ship s2){
			String s1Name = s1.getName().toUpperCase();
			String s2Name = s2.getName().toUpperCase();
			return s1Name.compareTo(s2Name);
		}
	};
		
	
}

//Ship Object. Parse from file to this object, then write to XML file for use
//by SubSkipper Android.
package xmlParser;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Ship {
	
	
	private int ID;
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
	@XmlAttribute
	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}
	@XmlElement
	public void setType(int type) {
		this.type = type;
	}

	public String getTypeName() {
		return typeName;
	}
	@XmlElement
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getImagePath() {
		return imagePath;
	}
	@XmlElement
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public double getMaxSpeed() {
		return maxSpeed;
	}
	@XmlElement
	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public double getLength() {
		return length;
	}
	@XmlElement
	public void setLength(double length) {
		this.length = length;
	}

	public double getWidth() {
		return width;
	}
	@XmlElement
	public void setWidth(double width) {
		this.width = width;
	}

	public double getMast() {
		return mast;
	}
	@XmlElement
	public void setMast(double mast) {
		this.mast = mast;
	}

	public double getDraft() {
		return draft;
	}
	@XmlElement
	public void setDraft(double draft) {
		this.draft = draft;
	}

	public double getDisp() {
		return disp;
	}
	@XmlElement
	public void setDisp(double disp) {
		this.disp = disp;
	}

	public double getRefAspect() {
		return refAspect;
	}
	@XmlElement
	public void setRefAspect(double refAspect) {
		this.refAspect = refAspect;
	}

	@Override
	public String toString() {
		return "Ship [ID=" + ID + ", name=" + name + ", type=" + type + ", typeName=" + typeName + ", imagePath="
				+ imagePath + ", maxSpeed=" + maxSpeed + ", length=" + length + ", width=" + width + ", mast=" + mast
				+ ", draft=" + draft + ", disp=" + disp + ", refAspect=" + refAspect + "]";
	}
	
}

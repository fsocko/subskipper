//Ship Object. Parse from file to this object, then write to XML file for use
//by SubSkipper Android.
package xmlParser;

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

	//TODO: Should probably delete this later.
	public Ship() {
		
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

	public double getWidth() {
		return width;
	}

	public double getMast() {
		return mast;
	}

	public double getDraft() {
		return draft;
	}

	public double getDisp() {
		return disp;
	}

	public double getRefAspect() {
		return refAspect;
	}

	@Override
	public String toString() {
		return "Ship [name=" + name + ", type=" + type + ", typeName=" + typeName + ", imagePath=" + imagePath
				+ ", maxSpeed=" + maxSpeed + ", length=" + length + ", width=" + width + ", mast=" + mast + ", draft="
				+ draft + ", disp=" + disp + ", refAspect=" + refAspect + "]";
	}
	
		
	
}

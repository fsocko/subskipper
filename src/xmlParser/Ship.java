//Ship Object. Parse from file to this object, then write to XML file for use
//by SubSkipper Android.
package xmlParser;

public class Ship {

	private String className;
	private int unitType;
	private String unitTypeName;
	private String imagePath;
	private double maxSpeed;
	private double length;
	private double width;
	private double mast;
	private double draft;
	private double disp;
	private double refAspect;
	
	//construct an empty ship
	public Ship(){
	
		this.className = "";
		this.imagePath = ""; 
		this.unitType = 0;
		this.maxSpeed = 0;
		this.length = 0;
		this.width = 0;
		this.mast = 0;
		this.draft = 0;
		this.disp = 0;
		this.refAspect = 0;
	}
	
	public Ship(String className, int unitType, double maxSpeed, double length,
			double width, double mast, double draft, double disp) {

				this.className = className;
				this.imagePath = this.className + "_sil.dds";
				//This seems to be how ship images are assigned paths in SCAF
				this.unitType = unitType;
				this.unitTypeName = ""; //TODO: set this once we are reading names.cfg
				this.maxSpeed = maxSpeed;
				this.length = length;
				this.width = width;
				this.mast = mast;
				this.draft = draft;
				this.disp = disp;
				this.refAspect = length / mast;
				//pre-calculating this for the aspect AOB method ought to make everything run faster.
				//cost in storage is negligible
				
			}

	public String getClassName() {
		return className;
	}

	public int getUnitType() {
		return unitType;
	}

	public String getUnitTypeName() {
		return unitTypeName;
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
		return "Ship [className=" + className + ", unitType=" + unitType + ", unitTypeName=" + unitTypeName
				+ ", imagePath=" + imagePath + ", maxSpeed=" + maxSpeed + ", length=" + length + ", width=" + width
				+ ", mast=" + mast + ", draft=" + draft + ", disp=" + disp + ", refAspect=" + refAspect + "]";
	}
	
	
	
}

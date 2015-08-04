//Ship Object. Parse from file to this object, then write to XML file for use
//by SubSkipper Android.
package xmlParser;

public class Ship {

	private String className;
	private int unitType;
	private String unitTypeName;
	private String image;
	private double maxSpeed;
	private double length;
	private double width;
	private double mast;
	private double draft;
	private double refAspect;
	
	//construct an empty ship
	public Ship(){
	
		this.className = "";
		this.image = ""; 
		this.unitType = 0;
		this.maxSpeed = 0;
		this.length = 0;
		this.width = 0;
		this.mast = 0;
		this.draft = 0;
		this.refAspect = 0;
	}
	
	public Ship(String className, int unitType, double maxSpeed, double length,
			double width, double mast, double draft) {

				this.className = className;
				this.image = this.className + "_sil.dds"; 
				//This seems to be how ship images are assigned paths in SCAF
				this.unitType = unitType;
				this.unitTypeName = ""; //TODO: set this once we are reading names.cfg
				this.maxSpeed = maxSpeed;
				this.length = length;
				this.width = width;
				this.mast = mast;
				this.draft = draft;
				this.refAspect = length / mast;
				//pre-calculating this for the aspect AOB method ought to make everything run faster.
				//cost in storage is negligible
				
			}
	
	public void setClassName(String className) {
		this.className = className;
	}

	public void setUnitType(int unitType) {
		this.unitType = unitType;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public void setMast(double mast) {
		this.mast = mast;
	}

	public void setDraft(double draft) {
		this.draft = draft;
	}

	@Override
	public String toString() {
		return "Ship [className=" + className + ", unitType=" + unitType + ", unitTypeName=" + unitTypeName + ", image="
				+ image + ", maxSpeed=" + maxSpeed + ", length=" + length + ", width=" + width + ", mast=" + mast
				+ ", draft=" + draft + ", refAspect=" + refAspect + "]";
	}


	
	
}

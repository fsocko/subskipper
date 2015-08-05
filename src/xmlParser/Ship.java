//Ship Object. Parse from file to this object, then write to XML file for use
//by SubSkipper Android.
package xmlParser;

public class Ship {

	private String name;
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
	
		this.name = "";
		this.imagePath = ""; 
		this.maxSpeed = 0;
		this.length = 0;
		this.width = 0;
		this.mast = 0;
		this.draft = 0;
		this.disp = 0;
		this.refAspect = 0;
	}
	
	public Ship(String name, int unitType, double maxSpeed, double length,
			double width, double mast, double draft, double disp) {

				this.name = name;
				this.imagePath = this.name + "_sil.dds";
				//This seems to be how ship images are assigned paths in SCAF
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

	public String getName() {
		return name;
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
		return "Ship [name=" + name + ", imagePath=" + imagePath + ", maxSpeed=" + maxSpeed + ", length=" + length
				+ ", width=" + width + ", mast=" + mast + ", draft=" + draft + ", disp=" + disp + ", refAspect="
				+ refAspect + "]";
	}


	
	
}

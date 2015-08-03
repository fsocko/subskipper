//Ship Object. Parse from file to this object, then write to XML file for use
//by SubSkipper Android.
package xmlParser;

public class Ship {

	private String className;
	private int unitType;
	private double maxSpeed;
	private double length;
	private double width;
	private double mast;
	private double draft;
	
	public Ship(String className, int unitType, double maxSpeed, double length,
	double width, double mast, double draft) {

		this.className = className;
		this.unitType = unitType;
		this.maxSpeed = maxSpeed;
		this.length = length;
		this.width = width;
		this.mast = mast;
		this.draft = draft;
	}
	
}

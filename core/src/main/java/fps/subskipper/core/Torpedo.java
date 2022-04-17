package fps.subskipper.core;

// TODO: Auto-generated Javadoc

public class Torpedo {

    private int Id;
    private String name;
    private double speedSlow;
    private double speedMedium;
    private double speedFast;
    private double rangeS;
    private double rangeMedium;
    private double rangeFast;

    /**
     * The impact Time for this solution / torpedo:
     * set by Stopwatch, so mostlikely nano seconds.
     */
    private double impactTime;

    // TODO: location:
    // bearing
    // distance
    // time away

    // TODO: get speed as L/M/H, determine speed value based on torpedo type and its properties.
    //

    /**
     * constructs a torpedo.
     *
     * @param name   torp Name
     * @param speedSlow slow speed
     * @param speedFast fast speed
     * @param speedM torp range, med
     * @param rangeS torp range, slow.
     * @param rangeFast torp range, fast.
     */

    // FIXME: add location data
    public Torpedo(String name, double speedSlow, double speedFast, double speedM, double rangeS, double rangeFast) {
        this.Id = 001;
        this.name = name;
        this.speedSlow = speedSlow;
        this.speedFast = speedFast;
        this.rangeS = rangeS;
        this.rangeFast = rangeFast;
        this.impactTime = 0;
    }

    public int getId() {
        return Id;
    }

    public void setId(int iD) {
        Id = iD;
    }

    public double getImpactTime() {
        return impactTime;
    }

    public void setImpactTime(double impactTime) {
        this.impactTime = impactTime;
    }

    public String getName() {
        return name;
    }

    public double getSpeedSlow() {
        return speedSlow;
    }

    public double getSpeedFast() {
        return speedFast;
    }

    public double getRangeS() {
        return rangeS;
    }

    public double getRangeFast() {
        return rangeFast;
    }

}

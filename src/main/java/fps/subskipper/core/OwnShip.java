package fps.subskipper.core;

public class OwnShip extends Ship {

    private int ownBearing = -1; //degree
    private int ownSpeed = -1; //kt


    @Override
    public String toString() {
        return "OwnShip [ownBearing=" + ownBearing + ", ownSpeed=" + ownSpeed + "]";
    }
}

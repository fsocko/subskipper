package coreLogic;

public class OwnShip {

    private int ownBearing = -1; //degree
    private int ownSpeed = -1; //kt


    @Override
    public String toString() {
        return "OwnShip [ownBearing=" + ownBearing + ", ownSpeed=" + ownSpeed + "]";
    }
}

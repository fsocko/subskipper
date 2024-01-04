package ship.data.reader;

import fps.subskipper.core.Ship;
import lombok.Getter;

@Getter
public class RMShip extends Ship {

    private final String image;

    public RMShip(String name, int type, String typeName, String imagePath, String image, double maxSpeed, double length, double width, double mast, double draft, double displacement) {
        super(name, type, typeName, imagePath, maxSpeed, length, width, mast, draft, displacement);
        this.image = image;
    }

    @Override
    public String toString() {
        return "RMShip{ " + super.toString() +
                "\n image=" + image +
                " } ";
    }
}

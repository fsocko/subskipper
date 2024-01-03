package ship.data.reader;

import fps.subskipper.core.Ship;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.awt.image.BufferedImage;

@Getter
@Setter
public class RMShip extends Ship {

    private BufferedImage image;

    public RMShip(String name, int type, String typeName, String imagePath, BufferedImage image, double maxSpeed, double length, double width, double mast, double draft, double displacement) {
        super(name, type, typeName, imagePath, maxSpeed, length, width, mast, draft, displacement);
        this.setImage(image);
    }

    @Override
    public String toString() {
        return "RMShip{ " + super.toString() +
                "\n image=" + image +
                " } ";
    }
}

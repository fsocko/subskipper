package fps.subskipper.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ShipsTest {

    @Test
    public void testGetShipList() {
        // Create a list of ships
        List<Ship> shipList = new ArrayList<>();
        shipList.add(new Ship("Ship1", 1, "Type1", "Image1", 10.0, 100.0, 50.0, 20.0, 10.0, 1000.0));
        shipList.add(new Ship("Ship2", 2, "Type2", "Image2", 20.0, 200.0, 100.0, 40.0, 20.0, 2000.0));
        Ships ships = new Ships(shipList);

        // Get the ship list
        List<Ship> retrievedShipList = ships.getShipList();

        // Verify that the retrieved ship list is the same as the original ship list
        Assertions.assertEquals(shipList, retrievedShipList);
    }

    @Test
    public void testToString() {
        // Create a list of ships
        List<Ship> shipList = new ArrayList<>();
        shipList.add(new Ship("Ship1", 1, "Type1", "Image1", 10.0, 100.0, 50.0, 20.0, 10.0, 1000.0));
        shipList.add(new Ship("Ship2", 2, "Type2", "Image2", 20.0, 200.0, 100.0, 40.0, 20.0, 2000.0));
        Ships ships = new Ships(shipList);

        // Get the string representation of the ships
        String shipsString = ships.toString();

        // Verify that the string representation matches the expected format
        String expectedString = "[Ship{id=1, nation='none', name='Ship1', type=1, typeName='Type1', imagePath='Image1', maxSpeed=10.0, length=100.0, width=50.0, mast=20.0, draft=10.0, displacement=1000.0, refAspect=5.0}, Ship{id=2, nation='none', name='Ship2', type=2, typeName='Type2', imagePath='Image2', maxSpeed=20.0, length=200.0, width=100.0, mast=40.0, draft=20.0, displacement=2000.0, refAspect=5.0}]";
        Assertions.assertEquals(expectedString, shipsString);
    }

    @Test
    public void testEqualsDifferentSize() {
        // Create two ships with different sizes
        List<Ship> shipList1 = new ArrayList<>();
        shipList1.add(new Ship("Ship1", 1, "Type1", "Image1", 10.0, 100.0, 50.0, 20.0, 10.0, 1000.0));
        Ships ships1 = new Ships(shipList1);

        List<Ship> shipList2 = new ArrayList<>();
        shipList2.add(new Ship("Ship1", 1, "Type1", "Image1", 10.0, 100.0, 50.0, 20.0, 10.0, 1000.0));
        shipList2.add(new Ship("Ship2", 2, "Type2", "Image2", 20.0, 200.0, 100.0, 40.0, 20.0, 2000.0));
        Ships ships2 = new Ships(shipList2);

        // Verify that the ships are considered not equal
        Assertions.assertNotEquals(ships1, ships2);
    }

    @Test
    public void testEqualsDifferentContent() {
        // Create two ships with different content
        List<Ship> shipList1 = new ArrayList<>();
        shipList1.add(new Ship("Ship1", 1, "Type1", "Image1", 10.0, 100.0, 50.0, 20.0, 10.0, 1000.0));
        Ships ships1 = new Ships(shipList1);

        List<Ship> shipList2 = new ArrayList<>();
        shipList2.add(new Ship("Ship2", 2, "Type2", "Image2", 20.0, 200.0, 100.0, 40.0, 20.0, 2000.0));
        Ships ships2 = new Ships(shipList2);

        // Verify that the ships are considered not equal
        Assertions.assertNotEquals(ships1, ships2);
    }
}
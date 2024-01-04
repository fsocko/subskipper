package fps.subskipper.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ShipsTest {

    @Test
    public void testSortShipsByType() {
        // Create a list of ships
        List<Ship> shipList = new ArrayList<>();
        shipList.add(new Ship("Ship1", "2", "Type2"));
        shipList.add(new Ship("Ship2", "1", "Type1"));
        shipList.add(new Ship("Ship3", "3", "Type3"));
        Ships ships = new Ships(shipList);

        // Sort ships by type
        ships.sortShipsByType();

        // Verify that the ships are sorted correctly
        List<Ship> expectedShipList = new ArrayList<>();
        expectedShipList.add(new Ship("Ship2", "1", "Type2"));
        expectedShipList.add(new Ship("Ship1", "2", "Type1"));
        expectedShipList.add(new Ship("Ship3", "3", "Type3"));
        Ships expectedShips = new Ships(expectedShipList);
        Assertions.assertEquals(expectedShips, ships);
    }

    @Test
    public void testSortShipsByName() {
        // Create a list of ships
        List<Ship> shipList = new ArrayList<>();
        shipList.add(new Ship("Ship3", "3", "Type1"));
        shipList.add(new Ship("Ship1", "1", "Type2"));
        shipList.add(new Ship("Ship2", "2", "Type3"));
        Ships ships = new Ships(shipList);

        // Sort ships by name
        ships.sortShipsByName();

        // Verify that the ships are sorted correctly
        List<Ship> expectedShipList = new ArrayList<>();
        expectedShipList.add(new Ship("Ship1", "1", "Type2"));
        expectedShipList.add(new Ship("Ship2", "2", "Type3"));
        expectedShipList.add(new Ship("Ship3", "3", "Type1"));

        Ships expectedShips = new Ships(expectedShipList);
        Assertions.assertEquals(expectedShips, ships);
    }

    @Test
    public void testGetShipByID() {
        // Create a list of ships
        List<Ship> shipList = new ArrayList<>();
        shipList.add(new Ship("Ship1", "1", "Type1"));
        shipList.add(new Ship("Ship2", "2", "Type2"));
        shipList.add(new Ship("Ship3", "3", "Type3"));
        Ships ships = new Ships(shipList);

        // Get ship by ID
        Ship ship = ships.getShipByID(2);

        // Verify that the correct ship is returned
        Ship expectedShip = new Ship("Ship2", "2", "Type2");
        Assertions.assertEquals(expectedShip, ship);
    }

    @Test
    public void testEquals() {
        // Create two ships with the same content
        List<Ship> shipList1 = new ArrayList<>();
        shipList1.add(new Ship("Ship1", "1", "Type1"));
        shipList1.add(new Ship("Ship2", "2", "Type2"));
        Ships ships1 = new Ships(shipList1);

        List<Ship> shipList2 = new ArrayList<>();
        shipList2.add(new Ship("Ship1", "1", "Type1"));
        shipList2.add(new Ship("Ship2", "2", "Type2"));
        Ships ships2 = new Ships(shipList2);

        // Verify that the ships are considered equal
        Assertions.assertEquals(ships1, ships2);
    }

    @Test
    public void testHashCode() {
        // Create a ship
        List<Ship> shipList = new ArrayList<>();
        shipList.add(new Ship("Ship1", "1", "Type1"));
        Ships ships = new Ships(shipList);

        // Calculate the hash code
        int hashCode = ships.hashCode();
        Assertions.assertEquals(1439632722, hashCode);
        // Verify that the hash code is consistent
        Assertions.assertEquals(hashCode, ships.hashCode());

        shipList.add(new Ship("Ship2", "2", "Type2"));
        Ships ships2 = new Ships(shipList);
        Assertions.assertEquals(1439632722, hashCode);
        Assertions.assertNotEquals(hashCode, ships.hashCode());
    }
}
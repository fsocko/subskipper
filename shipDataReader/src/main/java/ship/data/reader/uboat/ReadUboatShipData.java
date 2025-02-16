package ship.data.reader.uboat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import fps.subskipper.core.Ship;
import fps.subskipper.core.Ships;
import fps.subskipper.util.Constants;
import ship.data.reader.IShipDataReader;

public class ReadUboatShipData implements IShipDataReader {

    private Set<String> setOfValidCategories;
    private int skipHeaders = 2;

    public ReadUboatShipData() {
        if (setOfValidCategories == null) {
            setOfValidCategories = new HashSet<>();

            setOfValidCategories.add("Battleship");
            setOfValidCategories.add("Coaster");
            setOfValidCategories.add("Corvette");
            setOfValidCategories.add("Cruiser");
            setOfValidCategories.add("Destroyer");
            setOfValidCategories.add("Escort Carrier");
            setOfValidCategories.add("Fast Attack Craft");
            setOfValidCategories.add("Fishing Boat");
            setOfValidCategories.add("Freighter");
            setOfValidCategories.add("Tanker");
        }
    }

    private enum UboatColumn {
        NAME(0),
        CATEGORY(1),
        SPEED(2),
        STANDARD(3),
        FULL(4),
        LENGTH(5),
        BEAM(6),
        DRAUGHT(7),
        MAST_HEIGHT(8),
        GRT(9),
        RANGE(10),
        CREW(11),
        THREAT(12),
        MILITARY(13),
        IS_CLASS(14),
        REWARD(15),
        IRON_CROSS(16),
        ESTIMATED_DURABILITY(17),
        PARAMETERS(18),
        PREFAB_PATH(19),
        RECOGNIZED_AS(20);

        private final int index;

        private UboatColumn(int index) {
            this.index = index;
        }

        public int getIndex() {
            return index;
        }
    }

    @Override
    public Ships readShipsFromData(File shipDataPath) {

        List<Ship> shipList = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(new File(shipDataPath.toURI()));
             XSSFWorkbook workbook = new XSSFWorkbook(file);) {

            // Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);
            Ship currentShip;

            // Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                // For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();

                currentShip = new Ship();

                if (row.getRowNum() > skipHeaders) {

                    boolean validShipCategory = false;

                    Cell nameCell = row.getCell(UboatColumn.NAME.getIndex());
                    if (nameCell != null) {
                        currentShip.setName(nameCell.getStringCellValue());
                    }

                    Cell categoryCell = row.getCell(UboatColumn.CATEGORY.getIndex());
                    if (categoryCell != null) {
                        currentShip.setTypeName(categoryCell.getStringCellValue());
                        validShipCategory = setOfValidCategories.contains(categoryCell.getStringCellValue());
                    }

                    if (validShipCategory) {

                        Cell speedCell = row.getCell(UboatColumn.SPEED.getIndex());
                        if (speedCell != null) {
                            currentShip.setMaxSpeed(Constants.KNOTS_FOR_EVERY_KILOMETRE_PER_HOUR / speedCell.getNumericCellValue());
                        }

                        Cell standardCell = row.getCell(UboatColumn.STANDARD.getIndex());
                        if (standardCell != null) {
                            currentShip.setDisplacement(standardCell.getNumericCellValue());
                        }
                        /*
                         * Uboat data file has a standard and full displacement. We don't use this for
                         * anything important so we will take the average and not bother with full displacement.
                         */
                        Cell fullCell = row.getCell(UboatColumn.FULL.getIndex());
                        if (fullCell != null) {
                            double standardDisplacement = currentShip.getDisplacement();
                            currentShip.setDisplacement((standardDisplacement + fullCell.getNumericCellValue()) / 2);
                        }

                        Cell lengthCell = row.getCell(UboatColumn.LENGTH.getIndex());
                        if (lengthCell != null) {
                            currentShip.setLength(standardCell.getNumericCellValue());
                        }

                        Cell beamCell = row.getCell(UboatColumn.BEAM.getIndex());
                        if (beamCell != null) {
                            currentShip.setWidth(beamCell.getNumericCellValue());
                        }

                        Cell draughtCell = row.getCell(UboatColumn.DRAUGHT.getIndex());
                        if (draughtCell != null) {
                            currentShip.setDisplacement(draughtCell.getNumericCellValue());
                        }

                        Cell mastHeightCell = row.getCell(UboatColumn.MAST_HEIGHT.getIndex());
                        if (mastHeightCell != null) {
                            currentShip.setMast(mastHeightCell.getNumericCellValue());
                        }

                        System.out.println("SHIP read and added:" + currentShip.toString());
                        shipList.add(currentShip);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Ships(shipList);
    }

}

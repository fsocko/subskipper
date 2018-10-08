package fps.subskipper.util;

public class Constants {

    public  Constants(){
        this.RESOURCES_PATH = Find.findFile(System.getProperty("user.dir"), "resources").getAbsolutePath();
        this.SHIPLIST_PATH = Find.findFile(System.getProperty("user.dir"), "resources").getAbsolutePath();
        this.SCAF_ROOT_PATH = Find.findFile(System.getProperty("user.dir"), "SCAF for TMO_2").getAbsolutePath();
        this.SCAF_NAMES_PATH = Find.findFile(SCAF_ROOT_PATH, "Names.cfg").getAbsolutePath();
        this.RECOGNITION_MANUALS_PATH = RESOURCES_PATH + "\\recognitionManuals";
    }

  //Conversion constants
  public static final double KNOTS_FOR_EVERY_METRE_PER_SECOND = 1.94384449;
  public static final double KNOTS_FOR_EVERY_KILOMETRE_PER_HOUR = 1.8520000;
  public static final double METRES_PER_MINUTE_FOR_EVERY_KNOT = 0.0323974082;
  public static final double FEET_FOR_EVERY_METRE = 3.28084;

  //PATHS
  public  static String RESOURCES_PATH;
  public  static String SHIPLIST_PATH;
  public  static String SCAF_ROOT_PATH;
  public  static String SCAF_NAMES_PATH;
  public  static String RECOGNITION_MANUALS_PATH;

  //Units
  public  static final String UNIT_DEGREE = "\u00b0";
  public  static final String UNIT_FOOT = "ft";
  public  static final String UNIT_METRE = "m";
  public  static final String UNIT_GROSS_TONS = "GRT";
  public  static final String UNIT_KNOT = "kt";

}


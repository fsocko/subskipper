package ship.data.reader;

import java.io.File;

public class Constants {

		public static String SFS = File.separator;
	   	public static String RESOURCES_PATH = System.getProperty("user.dir")+SFS+"src"+SFS+"main"+SFS+"resources";
	    public static String SHIPLIST_PATH = RESOURCES_PATH +SFS+"shipList.xml";
	    public static String SCAF_ROOT_PATH = RESOURCES_PATH +SFS+"\\SCAF for TMO_2";
	    public static String SCAF_DATA_PATH = SCAF_ROOT_PATH +SFS+"\\SCAF for TMO_2"+SFS+"Data";
	    public static String SCAF_NAMES_PATH = SCAF_ROOT_PATH +SFS+"Data"+SFS+"Roster"+SFS+"Names.cfg";
	    public static String TARGET_PATH = System.getProperty("user.dir")+SFS+"target";
	    
	
}

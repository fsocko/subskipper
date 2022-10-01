package fps.subskipper.core.util;

import org.apache.commons.lang3.StringUtils;

public class CoreStringUtils {
	
	private CoreStringUtils() {
		
	}
	
	public static String stripSpaces(String testString) {
		StringUtils.remove(testString, "\r" );
		StringUtils.remove(testString, "\n" );
		return StringUtils.remove(testString, " " );
	}
	
}

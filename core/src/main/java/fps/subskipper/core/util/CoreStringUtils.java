package fps.subskipper.core.util;

import org.apache.commons.lang3.StringUtils;

public class CoreStringUtils {
	
	private CoreStringUtils() {
		
	}
	
	public static String stripSpaces(String testString) {
		return StringUtils.remove( StringUtils.remove(StringUtils.remove(testString, "\r" ), "\n" ), " ");
	}
	
}

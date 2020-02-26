package com.eqtest.utility;

public class MiscUtil {
	
	public static String refinedNumberString(String str) {
		
		str = str.replaceAll("[^0-9]", "");
		return str;
		
	}

}

package com.freeman.utilities;

public class NumberUtil {

	public static long getLongValue(String numberString){
		if (numberString != null && numberString.trim().length() > 0){
			return (Long.parseLong(numberString));
		}
		return 0L;
	}
}

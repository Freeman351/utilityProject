/*
 * Created on 23-Aug-2007
 *
 */
package com.freeman.utilities;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

public class ConvertUtil implements Serializable {

	/**
	 * Convert string to date with provided format
	 * 
	 * @param dateStr
	 *            Date string
	 * @param format
	 *            Date format
	 * @return Date Trnasfered date
	 */
	public static Date stringToDate(String dateStr, String format) {
		SimpleDateFormat df = new SimpleDateFormat();
		df.applyPattern(format);
		try {
			return df.parse(dateStr);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * Format date to string
	 * 
	 * @param date
	 *            Date
	 * @param format
	 *            Date format
	 * @return String Date string
	 */
	public static String dateToString(Date date, String format) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat df = new SimpleDateFormat();
		df.applyPattern(format);
		return df.format(date);
	}

	/**
	 * Format the number to currency string
	 * 
	 * @param value
	 *            Currency number
	 * @return String Currency string with currency symble and format
	 */
	public static String toCurrency(double value) {
		NumberFormat cf = NumberFormat.getCurrencyInstance();
		cf.setGroupingUsed(true);
		String str = cf.format(value);
		return str;
	}

	/**
	 * Format the number to percentage string
	 * 
	 * @param value
	 *            The number need format
	 * @return String Percentage format string
	 */
	public static String toPercent(double value) {
		NumberFormat pf = NumberFormat.getPercentInstance();
		pf.setMinimumFractionDigits(2);
		pf.setMaximumFractionDigits(2);
		String str = pf.format(value);
		return str;
	}

	/**
	 * Split the string with given inner string
	 * 
	 * @param str
	 *            The string need to be splitted
	 * @param spStr
	 *            The string used to split
	 * @return String[] The string array after splitting
	 */
	public static String[] splitString(String str, String spStr) {
		List<String> spList = new ArrayList<String>();
		while (str.indexOf(spStr) >= 0) {
			spList.add(str.substring(0, str.indexOf(spStr)));
			str = str.substring(str.indexOf(spStr) + 1);
		}
		if (str.length() > 0)
			spList.add(str);
		return (String[]) spList.toArray(new String[spList.size()]);
	}

	/**
	 * Replace all content method
	 * 
	 * @param str
	 *            The string need to be processed
	 * @param oriStr
	 *            The string need to be replaced
	 * @param replaceStr
	 *            The string used to replace
	 * @return String
	 */
	public static String replaceAll(String str, String oriStr, String replaceStr) {
		if (str.indexOf(oriStr) < 0)
			return str;
		while (str.indexOf(oriStr) >= 0) {
			if (str.indexOf(oriStr) == 0) {
				str = replaceStr
						+ str.substring(str.indexOf(oriStr) + oriStr.length());
			} else {
				str = str.substring(0, str.indexOf(oriStr)) + replaceStr
						+ str.substring(str.indexOf(oriStr) + oriStr.length());
			}
		}
		return str;
	}

	/**
	 * Replace first content method
	 * 
	 * @param str
	 *            The string need to be processed
	 * @param oriStr
	 *            The string need to be replaced
	 * @param replaceStr
	 *            The string used to replace
	 * @return String
	 */
	public static String replace(String str, String oriStr, String replaceStr) {
		if (str.indexOf(oriStr) < 0)
			return str;
		if (str.indexOf(oriStr) == 0) {
			str = replaceStr
					+ str.substring(str.indexOf(oriStr) + oriStr.length());
		} else {
			str = str.substring(0, str.indexOf(oriStr)) + replaceStr
					+ str.substring(str.indexOf(oriStr) + oriStr.length());
		}
		return str;
	}

	/**
	 * Round the number with given digits
	 * 
	 * @param dight
	 * @param How
	 * @return double
	 */
	public static double roundDigit(double dight, double How) {
		dight = Math.round(dight * Math.pow(10, How)) / Math.pow(10, How);
		return dight;
	}

	public static String[] cutString(String str, int length) {
		List<String> strList = new ArrayList<String>();
		String remainStr = str;
		int count = 0;
		while (remainStr.length() > length && count < 9) {
			int cutLen = length;
			while (true) {
				String cutPosition = remainStr.substring(cutLen - 1, cutLen);
				if (!(cutPosition == null || cutPosition.equals(".")
						|| cutPosition.equals("\n") || cutPosition.equals(" "))) {
					// Recalculate the cut point
					cutLen--;
					if (cutLen <= 0) {
						cutLen = length;
						break;
					}
				} else {
					break;
				}
			}
			String cutStr = str.substring(0, cutLen);
			// Remove CR
			cutStr.replace("\r", "");
			cutStr.replace("\n", "");
			remainStr = str.substring(cutLen);
			str = remainStr;
			strList.add(cutStr);
			count++;
		}
		strList.add(remainStr);
		return (String[]) strList.toArray(new String[strList.size()]);
	}

	public static String fillString(String str, int dig, char fillChr) {
		if (str == null){
			str = "";
		}
		String retStr = "";
		for (int i = str.length(); i < dig; i++) {
			retStr += fillChr;
		}
		retStr += str;
		return retStr;
	}

	public byte[] InputStreamToByte(InputStream iStrm) throws IOException {
		ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
		int ch;
		while ((ch = iStrm.read()) != -1) {
			bytestream.write(ch);
		}
		byte imgdata[] = bytestream.toByteArray();
		bytestream.close();
		return imgdata;
	}

	public static Integer dateToInt(Date date) {
		String dateStr = dateToString(date, "yyyyMMdd");
		int dateInt = 0;
		try {
			dateInt = Integer.parseInt(dateStr);
		} catch (Exception e) {
			return 0;
		}

		return new Integer(dateInt);
	}

	public static Date intToDate(int date) {
		String dateStr = String.valueOf(date);
		Date dDate = null;
		try {
			dDate = stringToDate(dateStr, "yyyyMMdd");
		} catch (Exception e) {
			return null;
		}

		return dDate;
	}

	public static String getFileExt(String fileName) {
		String ext = fileName.substring(fileName.lastIndexOf(".") + 1, fileName
				.length());
		return ext;
	}

	public static String getHtmlContent(String htmlStr) {
		String prefix = "";
		while (htmlStr.indexOf('<') >= 0) {
			int begin = htmlStr.indexOf('<');
			int end = htmlStr.indexOf('>');
			if (begin != 0) {
				prefix = htmlStr.substring(0, begin);
			}
			htmlStr = prefix + htmlStr.substring(end + 1);
		}
		htmlStr = htmlStr.replaceAll("\n", "");
		htmlStr = htmlStr.replaceAll("\r", "");
		return htmlStr;
	}

	/**
	 * Format the number to currency string
	 * 
	 * @param value
	 *            Currency number
	 * @return String Currency string with currency symble and format
	 */
	public static String formatCurrency(double value, Locale loc) {
		NumberFormat cf = NumberFormat.getNumberInstance(loc);
		cf.setGroupingUsed(true);
		cf.setMaximumFractionDigits(2);
		String str = cf.format(value);
		return str;
	}

	public static <E> Vector<E> list2Vector(List<E> list) {
		Vector<E> v = new Vector<E>();
		if (list != null && !list.isEmpty()) {
			for (E e : list) {
				v.add(e);
			}
		}
		return v;
	}

	public static Integer timeToInt(Date date) {
		String dateStr = dateToString(date, "HHmmss");
		int dateInt = 0;
		try {
			dateInt = Integer.parseInt(dateStr);
		} catch (Exception e) {
			return 0;
		}

		return new Integer(dateInt);
	}

	public static Date intToDate(int date, int time) {
		String dateStr = String.valueOf(date);
		String timeStr = String.valueOf(time);
		if (timeStr.length() < 6) {
			timeStr = fillString(timeStr, 6, '0');
		}
		Date dDate = null;
		try {
			dDate = stringToDate(dateStr + "-" + timeStr, "yyyyMMdd-HHmmss");
		} catch (Exception e) {
			return null;
		}

		return dDate;
	}

	public static String null2Empty(String str) {
		return str == null ? "" : HtmlUtil.frAccent2En(str);
	}
	
	public static boolean numberFormatForPhone(String str){
		boolean returnVal = true;
		for(int i=0; i<str.length(); i++){
			char strChar = str.charAt(i);
			String compStr = "";
			int comInt = 0;			
			compStr = String.valueOf(strChar);
						
			if(("-".equals(compStr)) || (" ".equals(compStr))){
				returnVal = true;
			}else{
				try{
					comInt = Integer.parseInt(compStr);
				}catch(NumberFormatException ne){
					returnVal = false;
				}
			}			
		}
		return returnVal;
	}
}

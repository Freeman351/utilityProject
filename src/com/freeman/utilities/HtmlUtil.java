/*
 * Created on 22-Nov-2007
 *
 */
package com.freeman.utilities;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Locale;

public class HtmlUtil implements Serializable {
	/*
	 * "&lt;" represents the < sign. "&gt;" represents the > sign. "&amp;"
	 * represents the & sign. "&quot;" represents the " mark.
	 */
	public static String enCodeHtml(String htmlStr) {
		if (htmlStr == null) {
			return " ";
		}
		StringBuffer retStr = new StringBuffer(htmlStr.length());
		for (int i = 0; i < htmlStr.length(); i++) {
			char chr = htmlStr.charAt(i);
			retStr.append(strToHtml(chr));
		}
		return retStr.toString();
	}

	public static String enCodeStr(String htmlStr) {
		String retStr = "";
		for (int i = 0; i < htmlStr.length(); i++) {
			char chr = htmlStr.charAt(i);
			retStr += parseStr(chr);
		}
		return retStr;
	}

	private static String parseStr(char normalChr) {
		String htmlStr = "";
		switch (normalChr) {
		case '<':
			htmlStr = "&lt;";
			break;
		case '>':
			htmlStr = "&gt;";
			break;
		case '&':
			htmlStr = "&amp;";
			break;
		case '"':
			htmlStr = "&quot;";
			break;
		case 39:
			htmlStr = "&apos;";
			break;
		default:
			htmlStr = String.valueOf(normalChr);
		}
		return htmlStr;
	}

	private static String strToHtml(char normalChr) {
		String htmlStr = "";
		switch (normalChr) {
		case '<':
			htmlStr = "&lt;";
			break;
		case '>':
			htmlStr = "&gt;";
			break;
		case '&':
			htmlStr = "&amp;";
			break;
		case '"':
			htmlStr = "&quot;";
			break;
		case 39:
			htmlStr = "&apos;";
			break;
		default:
			htmlStr = String.valueOf(normalChr);
		}
		return htmlStr;
	}

	public static String cleanApos(String htmlStr) {
		return htmlStr.replaceAll("'", "''");
	}

	public static String fr2En(String content, Locale loc) {
		if (content == null || content.equals("")) {
			return content;
		}
		String frCode = "iso-8859-15";
		if (loc.getLanguage().equals(Locale.FRENCH.getLanguage())) {
			frCode = "iso-8859-1";
		}
		try {
			content = new String(content.getBytes(frCode), "utf8");
		} catch (UnsupportedEncodingException e) {
			try {
				content = new String(content.getBytes(frCode), "utf-8");
			} catch (UnsupportedEncodingException e1) {
			}
		}
		return content;
	}

	public static String frAccent2En(String content) {
		if (content == null || content.equals("")){
			return content;
		}
		content = content.replaceAll("&Agrave;", "À");
		content = content.replaceAll("&Aacute;", "Á");
		content = content.replaceAll("&Acirc;", "Â");
		content = content.replaceAll("&Atilde;", "Ã");
		content = content.replaceAll("&Auml;", "Ä");
		content = content.replaceAll("&Aring;", "Å");
		content = content.replaceAll("&AElig;", "Æ");
		content = content.replaceAll("&Ccedil;", "Ç");
		content = content.replaceAll("&Egrave;", "È");
		content = content.replaceAll("&Eacute;", "É");
		content = content.replaceAll("&Ecirc;", "Ê");
		content = content.replaceAll("&Euml;", "Ë");
		content = content.replaceAll("&Igrave;", "Ì");
		content = content.replaceAll("&Iacute;", "Í");
		content = content.replaceAll("&Icirc;", "Î");
		content = content.replaceAll("&Iuml;", "Ï");
		content = content.replaceAll("&ETH;", "Ð");
		content = content.replaceAll("&Ntilde;", "Ñ");
		content = content.replaceAll("&Ograve;", "Ò");
		content = content.replaceAll("&Oacute;", "Ó");
		content = content.replaceAll("&Ocirc;", "Ô");
		content = content.replaceAll("&Otilde;", "Õ");
		content = content.replaceAll("&Ouml;", "Ö");
		content = content.replaceAll("&Oslash;", "Ø");
		content = content.replaceAll("&Ugrave;", "Ù");
		content = content.replaceAll("&Uacute;", "Ú");
		content = content.replaceAll("&Ucirc;", "Û");
		content = content.replaceAll("&Uuml;", "Ü");
		content = content.replaceAll("&Yacute;", "Ý");
		content = content.replaceAll("&THORN;", "Þ");
		content = content.replaceAll("&szlig;", "ß");
		content = content.replaceAll("&agrave;", "à");
		content = content.replaceAll("&aacute;", "á");
		content = content.replaceAll("&acirc;", "â");
		content = content.replaceAll("&atilde;", "ã");
		content = content.replaceAll("&auml;", "ä");
		content = content.replaceAll("&aring;", "å");
		content = content.replaceAll("&aelig;", "æ");
		content = content.replaceAll("&ccedil;", "ç");
		content = content.replaceAll("&egrave;", "è");
		content = content.replaceAll("&eacute;", "é");
		content = content.replaceAll("&ecirc;", "ê");
		content = content.replaceAll("&euml;", "ë");
		content = content.replaceAll("&igrave;", "ì");
		content = content.replaceAll("&iacute;", "í");
		content = content.replaceAll("&icirc;", "î");
		content = content.replaceAll("&iuml;", "ï");
		content = content.replaceAll("&eth;", "ð");
		content = content.replaceAll("&ntilde;", "ñ");
		content = content.replaceAll("&ograve;", "ò");
		content = content.replaceAll("&oacute;", "ó");
		content = content.replaceAll("&ocirc;", "ô");
		content = content.replaceAll("&otilde;", "õ");
		content = content.replaceAll("&ouml;", "ö");
		content = content.replaceAll("&oslash;", "ø");
		content = content.replaceAll("&ugrave;", "ù");
		content = content.replaceAll("&uacute;", "ú");
		content = content.replaceAll("&ucirc;", "û");
		content = content.replaceAll("&uuml;", "ü");
		content = content.replaceAll("&yacute;", "ý");
		content = content.replaceAll("&thorn;", "þ");
		content = content.replaceAll("&yuml;", "ÿ");
		content = content.replaceAll("&OElig;", "Œ");
		content = content.replaceAll("&oelig;", "œ");
		content = content.replaceAll("&Scaron;", "Š");
		content = content.replaceAll("&scaron;", "š");
		content = content.replaceAll("&Yuml;", "Ÿ");
		content = content.replaceAll("&laquo;", "«");
		content = content.replaceAll("&raquo;", "»");
		content = content.replaceAll("&lsaquo;", "‹");
		content = content.replaceAll("&rsaquo;", "›");
		content = content.replaceAll("&euro;", "€");
		return content;
	}

	public static boolean checkFrenchAccent(String str) {
		for (int i = 0; i < str.length(); i++) {
			char chr = str.charAt(i);
			if (chr >= 128 && chr <= 255) {
				return true;
			}
		}
		return false;
	}

	public static String rearrangeTextByLength(String str, int length) {
		String htmlStr = "";
		String[] lines = str.split("\r\n");
		for (int j = 0; j < lines.length; j++) {
			String[] elements = ConvertUtil.cutString(lines[j], length);
			for (int i = 0; i < elements.length; i++) {
				htmlStr += elements[i] + "<br>";
			}
		}
		return htmlStr;
	}

	public static String removeBr(String str) {
		if (str == null || str.equals("")) {
			return str;
		}
		str = ConvertUtil.replaceAll(str, "<br>", "");
		return str;

	}

	public static String enCodeUrl(String url) {
		try {
			return URLEncoder.encode(url.toString(), "ISO-8859-1");
		} catch (Exception e) {
			return url;
		}
	}

	public static String deCodeUrl(String url) {
		try {
			return URLDecoder.decode(url.toString(), "ISO-8859-1");
		} catch (Exception e) {
			return url;
		}
	}

	final static String[] FRENCH_ACCENTS = { "À", "Â", "É", "Ê", "Ë", "Î", "Ï",
			"Ù", "Û", "Ÿ", "à", "â", "é", "ê", "ë", "î", "ï", "ù", "û", "ÿ",
			"ç", "è", "ô" };
	final static String[] FRENCH_ENG = { "A", "A", "E", "E", "E", "I", "I",
			"U", "U", "Y", "a", "a", "e", "e", "e", "i", "i", "u", "u", "y",
			"c", "e", "o" };

	public static String removeFrenchAccent(String str) {
		for (int i = 0; i < str.length(); i++) {
			String chr = String.valueOf(str.charAt(i));
			for (int j = 0; j < FRENCH_ACCENTS.length; j++) {
				if (FRENCH_ACCENTS[j].equals(chr)) {
					str = str.replaceAll(chr, FRENCH_ENG[j]);
				}
			}
		}
		return str;
	}

}

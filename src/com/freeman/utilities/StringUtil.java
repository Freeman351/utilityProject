package com.freeman.utilities;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class StringUtil {

	/**
	 * sanitize path-elements and remove empty path-elements
	 *
	 * @param uri
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static List<String> parseAndSanitizeParameters(String uri) throws UnsupportedEncodingException {
		ArrayList<String> resultList = new ArrayList<String>();
		if(uri.startsWith("/"))uri = uri.substring(1);
        String [] pathArray = uri.split("\\/");
		//System.out.println("uri = '"+uri+"', pathArray.length = " + pathArray.length);
		for (String tmp : pathArray) {
			//if(tmp == null || tmp.length() == 0) continue;
			tmp = URLDecoder.decode(tmp,"UTF-8");
			tmp = tmp.replaceAll(" ","_");
			resultList.add(tmp);
		}
		return resultList;
	}

	public static String urlFriendly(String src){
		StringBuffer  sb = new StringBuffer();
		char[] srcBuf = src.toCharArray();
		for (int i = 0; i < srcBuf.length; i++) {
			if(srcBuf[i] <= 0x20){
				continue;
			}
			if((srcBuf[i] > 0x20 && srcBuf[i] <= 0x2F)|| srcBuf[i] == 0x5C){
				sb.append('-'); // '-'
				continue;
			}
			sb.append(srcBuf[i]);
		}
		return sb.toString();
	}

	public static void printMap(HashMap map) {
		if(map == null || map.isEmpty() ){
			System.out.println("empty map standard get mapping from request will be called");
			return;
		}
		Set keySet = map.keySet();
		for (Iterator iterator = keySet.iterator(); iterator.hasNext();) {
			Object key= (Object) iterator.next();
			System.out.println("'"+key+"':'"+map.get(key)+"'");
		}
	}

	public static void printMapOfArrays(HashMap map) {
		if(map == null || map.isEmpty() ){
			System.out.println("empty map standard get mapping from request will be called");
			return;
		}
		Set keySet = map.keySet();
		for (Iterator iterator = keySet.iterator(); iterator.hasNext();) {
			Object key= (Object) iterator.next();
			String[] array = (String[])map.get(key);
			StringBuffer sb = new StringBuffer();
			for (String string : array) {
				sb.append(string).append(",");
			}
			System.out.println("'"+key+"':'"+sb.toString()+"'");
		}
	}
	
	public static String replaceBy(String oldString, String replace, String replaceBy) {
		String a = oldString.replaceAll(replace, replaceBy);
		return a;
		
	}
	
	public static String adBoldTagAroudText(String source, String text){
        String sourceLower = source.toLowerCase();
        String searchStringLower = text.toLowerCase();

        StringBuffer targetBuffer = new StringBuffer();
        int previousIdx = 0;
        int nextIdx = sourceLower.indexOf(searchStringLower, previousIdx);
        if (nextIdx == -1)
        	return source;
        while(nextIdx != -1) {
        	String replacement = source.substring(nextIdx, nextIdx + text.length());
        	targetBuffer.append(source.substring(previousIdx, nextIdx));
        	targetBuffer.append("<b>" + replacement + "</b>");
        	previousIdx = nextIdx + text.length();
            nextIdx = sourceLower.indexOf(searchStringLower, previousIdx);        	
        }
        targetBuffer.append(source.substring(previousIdx));
        return targetBuffer.toString();		
	}
	
	public static void main(String[] args) {
		String searchText = "eos";
		String originalString1 = "EOS is of aEoS aa Eosaa eOS";
		String originalString2 = "hdhfkdfhd";
		String originalString3 = "hs eos dhfkdfhd";
		String originalString4 = "hsdhfkdfhdEOS";
		
//		String nextString = originalString.replaceAll("(?i)" + searchText, "bb");
		System.out.println(originalString1);
		System.out.println(adBoldTagAroudText(originalString1, searchText));
		System.out.println(originalString2);
		System.out.println(adBoldTagAroudText(originalString2, searchText));
		System.out.println(originalString3);
		System.out.println(adBoldTagAroudText(originalString3, searchText));
		System.out.println(originalString4);
		System.out.println(adBoldTagAroudText(originalString4, searchText));
	}

}

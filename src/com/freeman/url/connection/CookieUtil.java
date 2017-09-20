package com.freeman.url.connection;

import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;

public class CookieUtil {
	public static void main(String[] args) throws Exception {
		URL url = new URL("http://vasudv05.cusa.canon.com:9300/action=query&text=D");
		URLConnection conn = url.openConnection();
		InputStream inputStream = conn.getInputStream();
		StringWriter writer = new StringWriter();
		IOUtils.copy(inputStream, writer, "UTF-8");
		String theString = writer.toString();

		
		System.out.println(theString);
	}

}

package com.freeman.utilities;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;

@SuppressWarnings("serial")
public class GeoIPLocator implements Serializable {

	public static boolean isIpMatchCountry(String ip, String country) {
		Locale clientLocale = getLocatorByIp(ip);
		if (clientLocale != null
				&& clientLocale.getCountry().equalsIgnoreCase(country)) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println("IP: 96.45.2.69 is in "
				+ getLocatorByIp("96.45.2.69"));
		System.out.println("IP: 96.45.2.69 is in Canada? "
				+ isIpMatchCountry("96.45.2.69", Locale.CANADA.getCountry()));
	}

	public static Locale getLocatorByIp(String ip) {
		Locale clientLocale = null;
		try {
			URL url = new URL("http://xml.utrace.de/?query=" + ip);
			URLConnection connection = url.openConnection();
			connection.setDoInput(true);
			InputStream inStream = connection.getInputStream();
			BufferedReader input = new BufferedReader(new InputStreamReader(
					inStream));

			String line = "";
			while ((line = input.readLine()) != null) {
				if (line.indexOf("countrycode") >= 0) {
					String countryCode = ConvertUtil.replace(line,
							"<countrycode>", "");
					countryCode = ConvertUtil.replace(countryCode,
							"</countrycode>", "");
					clientLocale = new Locale("",countryCode);
				}
			}
			inStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientLocale;
	}
}
package com.freeman.url.connection;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.codec.binary.Base64;

public class HttpsConnectionWithBasicAuthentication{
	
	public static void main(String[] args)throws Exception{
		String httpsURL = "https://pfe3trial.printfleet.com:443/restapi/3.5.3/auth?format=json";
		URL myurl = new URL(httpsURL);
		HttpsURLConnection con = (HttpsURLConnection)myurl.openConnection();
		String userpass = "batch4canon" + ":" + "canon4batch";
		String basicAuth = "Basic " + new String(new Base64().encode(userpass.getBytes()));
		con.setRequestProperty ("Authorization", basicAuth);
		InputStream ins = con.getInputStream();
		InputStreamReader isr = new InputStreamReader(ins);
		BufferedReader in = new BufferedReader(isr);

		String inputLine;

		while ((inputLine = in.readLine()) != null)
		{
			System.out.println(inputLine);
		}

		in.close();
	}
}
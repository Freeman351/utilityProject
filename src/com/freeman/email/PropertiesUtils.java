package com.freeman.email;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtils {

	public static final String EMAIL_HOST_KEY = "emailHost";
	public static final String EMAIL_USER_ID_KEY = "emailUserId";
	public static final String EMAIL_PASSWORD_KEY = "emailPassword";
	public static final String EMAIL_ATTACHMENT_KEY = "emailAttachment";

	public static final String PF_LABELS_KEY = "printFleetLabels";
	public static final String PF_ROOT_URL_KEY = "printFleetRootURL";

	public static final String IWR_LABELS_KEY = "imageWareRemoteLabels";
	public static final String IWR_INDEX_OF_LABELS_KEY = "indexOfIWRLabels";

	public static void main(String[] args) {

		String propertiesFilePath = "app_config/meterCountConfig.properties";
		Properties properties = new Properties();

		try {
			properties= loadProperties(propertiesFilePath);
			System.out.println(properties.getProperty(EMAIL_HOST_KEY));
			System.out.println(properties.getProperty(EMAIL_USER_ID_KEY));
			System.out.println(properties.getProperty(EMAIL_PASSWORD_KEY));
			System.out.println(properties.getProperty(EMAIL_ATTACHMENT_KEY));
			System.out.println(properties.getProperty(PF_LABELS_KEY));
			System.out.println(properties.getProperty(PF_ROOT_URL_KEY));
			System.out.println(properties.getProperty(IWR_LABELS_KEY));
			System.out.println(properties.getProperty(IWR_INDEX_OF_LABELS_KEY));
		} catch (IOException e) {
			properties = null;
			System.out.println("properties file is null!");
		}
		
	}
	public static Properties loadProperties(String filePath) throws IOException{
		
		InputStream input = new FileInputStream(filePath);
		Properties properties = new Properties();
		properties.load(input);
		return properties;
		
	}
}
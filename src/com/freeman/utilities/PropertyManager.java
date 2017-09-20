package com.freeman.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertyManager implements Serializable {
	private static Logger logger = Logger.getLogger(PropertyManager.class);
			
	public Properties loadMonerisProperties() {
		return this.getPropertyFromFile("MONERIS_MANAGER_PROPERTIES");
	}
	
	public Properties getPropertyFromFile(String fileName) {
		Properties prop = new Properties();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(fileName));
			String readData = "";
			while ((readData = reader.readLine()) != null) {
				if (readData.indexOf("#") == 0 || readData.indexOf("=") <= 0) {
					continue;
				}
				String[] props = readData.split("=", 2);
				if (props.length != 2) {
					continue;
				}
				prop.setProperty(props[0].trim(), props[1].trim());
			}
			reader.close();
		} catch (Exception e) {
		}
		return prop;
	}

	public Properties loadAs400Properties() {
		
		if ((new File("AS400_MANAGER_PROPERTIES")).exists()) {	
			logger.info("Loading AS400_MANAGER_PROPERTIES from: " + "AS400_MANAGER_PROPERTIES");
			return this.getPropertyFromFile("AS400_MANAGER_PROPERTIES");
		}
		
		logger.info("Loading AS400_MANAGER_PROPERTIES from: " + "/" + "AS400_MANAGER_PROPERTIES");
		
		Properties prop = new Properties();
		InputStream is = getClass().getResourceAsStream("/" + "AS400_MANAGER_PROPERTIES");

		try {
			prop.load(is);
		} catch (IOException e) {
			logger.error(e);
		}
		return prop;
	}
	
	public String loadEnvProperties() {
		File file = new File("ENV_MANAGER_PROPERTIES");

		String env = FileUtil.getContents(file);

		return env;
	}
	
	public Properties loadProperties(String propertiesPath) {

		Properties prop = new Properties();
		InputStream is = getClass().getResourceAsStream("/" + propertiesPath);

		try {
			prop.load(is);
		} catch (IOException e) {
			logger.error(e);
		}
		return prop;
	}	
}

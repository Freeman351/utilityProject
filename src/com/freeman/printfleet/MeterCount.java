package com.freeman.printfleet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.freeman.printfleet.service.restful.PrintFleetRestfulService;

public class MeterCount {

	public static final String BEAN_XML_FILE = "beanContext.xml";
	public static final String USER_ID_VALUE = "batch4canon";
	public static final String PASSWORD_VALUE = "canon4batch";
	public static final String LABEL_VALUE = "CANON_101";
	public static final String GROUP_ID_VALUE = "a8bd97c5-dbce-410c-bdbb-9685d6e80028";
	public static final String START_DATE_VALUE = "2012-01-01";
	public static final String END_DATE_VALUE = "2014-10-28";
	public static final String DEVICE_ID_VALUE = "dbba1d71-b9cf-44b2-8e1a-0fd16f0e60a3";
	public static final String ATTRIBUTES_NAME_SERIALNUMBER = "SERIALNUMBER";
	
	
	public static void main(String[] args) {
		PrintFleetRestfulService pfrService = (PrintFleetRestfulService) new MeterCount() 
				.getApplicationContext(BEAN_XML_FILE).getBean("printFleetRestfulService");
		String response = new String();
		
		response = pfrService.getUserByCredentials(USER_ID_VALUE, PASSWORD_VALUE);
		System.out.println("User info==" + response);

		response = pfrService.getLabelsByGroupId(GROUP_ID_VALUE, USER_ID_VALUE, PASSWORD_VALUE);
		System.out.println("Labels info==" + response);
		
		response = pfrService.getDevicesByLable(GROUP_ID_VALUE, LABEL_VALUE, 
				START_DATE_VALUE, END_DATE_VALUE, USER_ID_VALUE, PASSWORD_VALUE);
		System.out.println("Devices info==" + response);

		response = pfrService.getDeviceAttributeByDeviceId(DEVICE_ID_VALUE, 
				ATTRIBUTES_NAME_SERIALNUMBER, USER_ID_VALUE, PASSWORD_VALUE);
		System.out.println("Device Attribute info==" + response);
	
		response = pfrService.getDevicesByGroupId(GROUP_ID_VALUE,
				USER_ID_VALUE, PASSWORD_VALUE);
		System.out.println("Devices info==" +response);

		response = pfrService.getSummaryOfDeviceByDeviceId(DEVICE_ID_VALUE, 
				USER_ID_VALUE, PASSWORD_VALUE);
		System.out.println("Summary Of Device info==" +response);

	}
	
	private ApplicationContext getApplicationContext(String beanXmlFileName){
		return  new ClassPathXmlApplicationContext(beanXmlFileName);
	}
	
}

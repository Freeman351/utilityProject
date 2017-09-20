package com.freeman.printfleet.service.restful;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestOperations;


public class PrintFleetRestfulServiceImpl implements PrintFleetRestfulService{

	public static final String FUNCATION_PARAMETER = "function";
	public static final String FUNCATION_AUTH = "auth";
	public static final String FUNCATION_METERS = "meters";
	public static final String FUNCATION_DEVICES = "devices";
	public static final String LABEL_PARAMETER = "label";
	public static final String DEVICE_ID_PARAMETER = "deviceId";
	public static final String FORMAT_PARAMETER = "format";
	public static final String FORMAT_VALUE_JSON = "json";
	public static final String GROUP_ID_PARAMETER = "groupId";
	public static final String START_DATE_PARAMETER = "startDate";
	public static final String END_DATE_PARAMETER = "endDate";
	public static final String ATTRIBUTES_PARAMETER = "attributes";
	
	private static Logger logger = Logger.getLogger(PrintFleetRestfulServiceImpl.class);

	private RestOperations restTemplate;
	private String baseURL;

	public String getUserByCredentials(String userName, String password) {
		String functionURL = baseURL + "/{" + FUNCATION_PARAMETER+ "}" 
				+ "?" + FORMAT_PARAMETER + "={" + FORMAT_PARAMETER + "}";
		
		Map<String, String> params = new HashMap<String, String>();
	    params.put(FUNCATION_PARAMETER, FUNCATION_AUTH);
	    params.put(FORMAT_PARAMETER, FORMAT_VALUE_JSON);
		
	    return execute(functionURL, HttpMethod.PUT , new HttpEntity<String>(createHeaders(userName, password)), params);
	}

	public String getLabelsByGroupId(String groupId, String userName, String password) {
		String functionURL = baseURL + "/{" + FUNCATION_PARAMETER+ "}" 
				+ "?" + FORMAT_PARAMETER + "={" + FORMAT_PARAMETER + "}" 
				+ "&" + GROUP_ID_PARAMETER + "={" + GROUP_ID_PARAMETER + "}";
		
		Map<String, String> params = new HashMap<String, String>();
	    params.put(FUNCATION_PARAMETER, FUNCATION_METERS);
	    params.put(FORMAT_PARAMETER, FORMAT_VALUE_JSON);
	    params.put(GROUP_ID_PARAMETER, groupId);
		
	    return execute(functionURL, HttpMethod.GET , new HttpEntity<String>(createHeaders(userName, password)), params);
	}
	
	public String getDevicesByLable(String groupId, String label, String startDate, String endDate, String userName, String password) {
		String functionURL = baseURL + "/{" + FUNCATION_PARAMETER+ "}/{" + LABEL_PARAMETER + "}" 
				+ "?" + FORMAT_PARAMETER + "={" + FORMAT_PARAMETER + "}" 
				+ "&" + GROUP_ID_PARAMETER + "={" + GROUP_ID_PARAMETER + "}"
				+ "&" + START_DATE_PARAMETER + "={" + START_DATE_PARAMETER + "}"
				+ "&" + END_DATE_PARAMETER + "={" + END_DATE_PARAMETER + "}";
		
		Map<String, String> params = new HashMap<String, String>();
	    params.put(FUNCATION_PARAMETER, FUNCATION_METERS);
	    params.put(LABEL_PARAMETER, label);
	    params.put(FORMAT_PARAMETER, FORMAT_VALUE_JSON);
	    params.put(GROUP_ID_PARAMETER, groupId);
	    params.put(START_DATE_PARAMETER, startDate);
	    params.put(END_DATE_PARAMETER, endDate);
		
	    return execute(functionURL, HttpMethod.GET , new HttpEntity<String>(createHeaders(userName, password)), params);
	}
	
	public String getDeviceAttributeByDeviceId(String deviceId, String attributeName, String userName, String password) {
		String functionURL = baseURL + "/{" + FUNCATION_PARAMETER + "}"
				+ "/{" + DEVICE_ID_PARAMETER + "}"
				+ "/" + ATTRIBUTES_PARAMETER + "/{" + ATTRIBUTES_PARAMETER + "}"
				+ "?" + FORMAT_PARAMETER + "={" + FORMAT_PARAMETER + "}"; 
		
		Map<String, String> params = new HashMap<String, String>();
	    params.put(FUNCATION_PARAMETER, FUNCATION_DEVICES);
	    params.put(DEVICE_ID_PARAMETER, deviceId);
	    params.put(ATTRIBUTES_PARAMETER, attributeName);
	    params.put(FORMAT_PARAMETER, FORMAT_VALUE_JSON);
		
	    return execute(functionURL, HttpMethod.GET , new HttpEntity<String>(createHeaders(userName, password)), params);
	}
	
	public String getDevicesByGroupId(String groupId, String userName, String password) {
		String functionURL = baseURL + "/{" + FUNCATION_PARAMETER + "}"
				+ "?" + FORMAT_PARAMETER + "={" + FORMAT_PARAMETER + "}"
				+ "&" + GROUP_ID_PARAMETER + "={" + GROUP_ID_PARAMETER + "}"; 
		
		Map<String, String> params = new HashMap<String, String>();
	    params.put(FUNCATION_PARAMETER, FUNCATION_DEVICES);
	    params.put(FORMAT_PARAMETER, FORMAT_VALUE_JSON);
	    params.put(GROUP_ID_PARAMETER, groupId);
		
	    return execute(functionURL, HttpMethod.GET , new HttpEntity<String>(createHeaders(userName, password)), params);
	}
	
	public String getSummaryOfDeviceByDeviceId(String deviceId, String userName, String password) {
		String functionURL = baseURL + "/{" + FUNCATION_PARAMETER + "}"
				+ "/{" + DEVICE_ID_PARAMETER + "}"
				+ "?" + FORMAT_PARAMETER + "={" + FORMAT_PARAMETER + "}"; 
		
		Map<String, String> params = new HashMap<String, String>();
	    params.put(FUNCATION_PARAMETER, FUNCATION_DEVICES);
	    params.put(DEVICE_ID_PARAMETER, deviceId);
	    params.put(FORMAT_PARAMETER, FORMAT_VALUE_JSON);
		
	    return execute(functionURL, HttpMethod.GET , new HttpEntity<String>(createHeaders(userName, password)), params);
	}

		
	private String execute(String httpsURL, HttpMethod httpMethod, HttpEntity<String> httpEntity) {
		ResponseEntity<String> response = restTemplate.exchange(httpsURL, httpMethod,  httpEntity, String.class);
		return response.getBody();
	}

	private String execute(String httpsURL, HttpMethod httpMethod, HttpEntity<String> httpEntity, Map<String, String> params) {
		if (params == null || params.isEmpty())
			execute(httpsURL, httpMethod,httpEntity);
		ResponseEntity<String> response = restTemplate.exchange(httpsURL, httpMethod,  httpEntity, String.class, params);
		return response.getBody();
	}
	
	private HttpHeaders createHeaders(String userName, String password){
		HttpHeaders headers =  new HttpHeaders();
		headers.add("Authorization", "Basic " + new String(new Base64().encode(new String(userName + ":" + password).getBytes())));
	    return headers;
	}
	
	public RestOperations getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(RestOperations restTemplate) {
		this.restTemplate = restTemplate;
	}

	public String getBaseURL() {
		return baseURL;
	}

	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}

}

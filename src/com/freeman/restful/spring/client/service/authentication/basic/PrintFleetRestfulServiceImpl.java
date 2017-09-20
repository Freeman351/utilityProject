package com.freeman.restful.spring.client.service.authentication.basic;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestOperations;


public class PrintFleetRestfulServiceImpl implements PrintFleetRestfulService{

	private static Logger logger = Logger.getLogger(PrintFleetRestfulServiceImpl.class);

	private RestOperations restTemplate;
	private String baseURL;

	public String getUserByCredentials(String parameterURL, String userName,
			String password) {
		return execute(baseURL + parameterURL, HttpMethod.PUT , new HttpEntity<String>(createHeaders(userName, password)));
	}

		
	private String execute(String httpsURL, HttpMethod httpMethod, HttpEntity<String> httpEntity) {
		String result = new String();
		ResponseEntity<String> response = restTemplate.exchange(httpsURL, httpMethod,  httpEntity, String.class);
		result = response.getBody();
		return result;
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

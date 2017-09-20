package com.freeman.restful.spring.client.service;


import java.util.HashMap;
import java.util.Map;

import org.springframework.web.client.RestOperations;

public class OceDriverDownloadServiceImpl implements OceDriverDownloadService{

	private RestOperations restTemplate;
	private String baseURL; 

	public String getProductFamilies()  {
        String searchUrl = baseURL +"/{argument}";
        Map<String, String> variables = new HashMap<String, String>(3);
        variables.put("argument", "famid"); 
        String result = new String();
    	result = restTemplate.getForObject(searchUrl, String.class, variables);
    	System.out.println(result);
		return result;
	}

	public String getProductsByFamily(String famliyId) {
        String searchUrl = baseURL + "/{argument}/{value}";
        Map<String, String> variables = new HashMap<String, String>();
        variables.put("argument", "pid");
        variables.put("value", famliyId);
        String result = restTemplate.getForObject(searchUrl, String.class, variables);
		System.out.println(result);
		return result;
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

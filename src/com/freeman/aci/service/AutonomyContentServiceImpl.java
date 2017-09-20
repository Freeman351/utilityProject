package com.freeman.aci.service;

import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestOperations;

import com.freeman.aci.model.AutnResponse;

public class AutonomyContentServiceImpl implements AutonomyContentService{

	private static Logger logger = Logger.getLogger(AutonomyContentServiceImpl.class);

	private RestOperations restTemplate;

	public AutnResponse getAutnResponse(String aciSearchURL) {
		return restTemplate.getForObject(aciSearchURL, AutnResponse.class);
	}
	
	public String getAutonomyContents(String aciSearchURL) {
        logger.debug("aciURL===" +  aciSearchURL);
        String result = new String();
		try {
			result = new String(restTemplate.getForObject(aciSearchURL, String.class).getBytes(), "UTF-8");
		} catch (RestClientException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        logger.debug(result);
		return result;
	}

	public RestOperations getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(RestOperations restTemplate) {
		this.restTemplate = restTemplate;
	}


}

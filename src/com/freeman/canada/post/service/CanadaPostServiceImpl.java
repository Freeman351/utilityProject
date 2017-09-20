package com.freeman.canada.post.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.xml.bind.JAXBException;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.freeman.canada.post.TestData4CanadaPost;
import com.freeman.canada.post.model.AuthorizedReturnRequest;
import com.freeman.canada.post.model.AuthorizedReturnResponse;
import com.freeman.utilities.JaxbXmlUtil;
import com.freeman.utilities.PropertyManager;

public class CanadaPostServiceImpl implements CanadaPostService{

	private static final String AUTHORIZATION_PARAMETER = "Authorization";
	private static final String ACCEPT_LANGUAGE_PARAMETER = "Accept-language";
	private static final String ACCEPT_PARAMETER = "Accept";
	private static final String CONTENT_TYPE_PARAMETER = "Content-Type";
	private static final String CANON_ID_IN_CANADA_POST_PARAMETER = "canonIdInCanadaPost";
	
	private static final String AUTHORIZATION_BASIC_VALUE = "Basic ";
	private static final String ACCEPT_LANGUAGE_ENGLISH_VALUE = "en_CA";
	private static final String ACCEPT_LANGUAGE_FRENCH_VALUE = "fr_CA";
	private static final String APPLICATION_PDF_VALUE = "application/pdf";	
	private static final String APPLICATION_AUTHORIZED_RETURN_V2_VALUE = "application/vnd.cpc.authreturn-v2+xml";
	private static final String APPLICATION_OPEN_RETURN_VALUE = "application/vnd.cpc.openreturn+xml";
	private static final String APPLICATION_OPEN_RETURN_V2_VALUE = "application/vnd.cpc.openreturn-v2+xml";

	private static final String EMPTY_BODY_4_POST = "Hello, HTTP Error 411!!!";
	private static final String LANGUAE_FRENCH = "fr";
	private static final String CANADA_POST_SERVICE_PROPERTIES = "com/freeman/resources/CanadaPostService.properties";

	private static final String URL_AUTHORIZATION_RETURN = "/rs/{canonIdInCanadaPost}/{canonIdInCanadaPost}/authorizedreturn";
	private static final String URL_OPEN_RETURN = "/rs/{canonIdInCanadaPost}/{canonIdInCanadaPost}/openreturn";
	
	private static final String PROPERTY_NAME_CANON_ID_IN_CANADA_POST_SERVICE = "CANADA_POST_SERVICE_USER_ID";
	private static final String PROPERTY_NAME_PASSWORD_IN_CANADA_POST_SERVICE = "CANADA_POST_SERVICE_PASSWORD";
	private static final String PROPERTY_NAME_CANADA_POST_SERVICE_ADDRES = "CANADA_POST_SERVICE_ADDRESS";
	private static final String PROPERTY_NAME_CANON_ID_IN_CANADA_POST = "CANON_ID_IN_CANADA_POST";
	private static final String PROPERTY_NAME_CONTRACT_ID_IN_CANADA_POST = "CONTRACT_ID_IN_CANADA_POST";
	

	private RestTemplate restTemplate;
	private PropertyManager propertyManager;

	public byte[] geAuthorizedReturnDocument(String language, AuthorizedReturnRequest authorizedReturnRequest) throws Exception {
//		String responseString = getAuthorizedReturn(language,  authorizedReturnRequest);
//		AuthorizedReturnResponse response = JaxbXmlUtil.parseXmlString(responseString, AuthorizedReturnResponse.class);
		AuthorizedReturnResponse response = getAuthorizedReturn(language,  authorizedReturnRequest, AuthorizedReturnResponse.class);
		return getArtifact(language, response.getAuthorizedReturnLinks().getAuthorizedReturnLink().get(0).getHref());
	}

	public byte[] geAuthorizedReturnDocument(String language, String xmlString) throws Exception {
		String responseString = getAuthorizedReturn(language, xmlString);
		AuthorizedReturnResponse response = JaxbXmlUtil.parseXmlString(responseString, AuthorizedReturnResponse.class);
		return getArtifact(language, response.getAuthorizedReturnLinks().getAuthorizedReturnLink().get(0).getHref());
	}

	public <T> T getAuthorizedReturn(String language, AuthorizedReturnRequest authorizedReturnRequest, Class<T> className) throws JAXBException {
		Map<String, String> params = new HashMap<String, String>();
	    params.put(CANON_ID_IN_CANADA_POST_PARAMETER, getCanonIdInCanadaPost());
	    return execute(getServiceAddress() + URL_AUTHORIZATION_RETURN, HttpMethod.POST, 
	    		new HttpEntity<String>(JaxbXmlUtil.generateXmlString(authorizedReturnRequest), getAuthorizedReturnHeaders(language)), 
	    		className, params);
	}
	
	public String getAuthorizedReturn(String language, String xmlString) {
		Map<String, String> params = new HashMap<String, String>();
	    params.put(CANON_ID_IN_CANADA_POST_PARAMETER, getCanonIdInCanadaPost());
	    return execute(getServiceAddress() + URL_AUTHORIZATION_RETURN, HttpMethod.POST, 
	    		new HttpEntity<String>(xmlString, getAuthorizedReturnHeaders(language)), params);
	}
	
	public byte[] getArtifact(String language, String functionURL) {
		ResponseEntity<byte[]> response = restTemplate.exchange(functionURL, HttpMethod.GET,  new HttpEntity<String>(getArtifactHeaders(language)), byte[].class);
		return response.getBody();
	}
	
	public String getOpenReturn(String language) {
		Map<String, String> params = new HashMap<String, String>();
	    params.put(CANON_ID_IN_CANADA_POST_PARAMETER, getCanonIdInCanadaPost());
	    return execute(getServiceAddress() + URL_OPEN_RETURN, HttpMethod.POST, 
	    		new HttpEntity<String>(TestData4CanadaPost.getOpenReturnRequest(getContractIdInCanadaPost()), getOpenReturnHeaders(language)), params);
	}

	public byte[] getReturnArtifact(String language, String functionURL) {
		ResponseEntity<byte[]> response = restTemplate.exchange(functionURL, HttpMethod.POST,  
				new HttpEntity<String>(EMPTY_BODY_4_POST, getReturnArtifactHeaders(language)), byte[].class);
		return response.getBody();
	}
	
	private HttpHeaders getAuthorizedReturnHeaders(String language){
		HttpHeaders headers =  new HttpHeaders();
		setBasicAuthrization(headers);
		setLanguage(headers, language);
		headers.add(ACCEPT_PARAMETER, APPLICATION_AUTHORIZED_RETURN_V2_VALUE);
		headers.add(CONTENT_TYPE_PARAMETER, APPLICATION_AUTHORIZED_RETURN_V2_VALUE);
	    return headers;                                
	}

	private HttpHeaders getArtifactHeaders(String language){
		HttpHeaders headers =  new HttpHeaders();
		setBasicAuthrization(headers);
		setLanguage(headers, language);
		headers.add(ACCEPT_PARAMETER, APPLICATION_PDF_VALUE);
	    return headers;                                
	}
	
	private HttpHeaders getOpenReturnHeaders(String language){
		HttpHeaders headers =  new HttpHeaders();
		setBasicAuthrization(headers);
		setLanguage(headers, language);
		headers.add(ACCEPT_PARAMETER, APPLICATION_OPEN_RETURN_V2_VALUE);
		headers.add(CONTENT_TYPE_PARAMETER, APPLICATION_OPEN_RETURN_V2_VALUE);
	    return headers;                                
	}

	private HttpHeaders getReturnArtifactHeaders(String language){
		HttpHeaders headers =  new HttpHeaders();
		setBasicAuthrization(headers);
		setLanguage(headers, language);
		headers.add(ACCEPT_PARAMETER, APPLICATION_PDF_VALUE);
		headers.add(CONTENT_TYPE_PARAMETER, APPLICATION_OPEN_RETURN_VALUE);
	    return headers;                                
	}
	
	private void setBasicAuthrization(HttpHeaders headers){
		headers.add(AUTHORIZATION_PARAMETER, AUTHORIZATION_BASIC_VALUE + new String(new Base64().encode(new String(getUserId() + ":" + getPassword()).getBytes())));
	}
	
	private void setLanguage(HttpHeaders headers, String language){
		if (language.equals(LANGUAE_FRENCH)){
			headers.add(ACCEPT_LANGUAGE_PARAMETER, ACCEPT_LANGUAGE_FRENCH_VALUE);
		}else{
			headers.add(ACCEPT_LANGUAGE_PARAMETER, ACCEPT_LANGUAGE_ENGLISH_VALUE);			
		}
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

	private <T> T execute(String httpsURL, HttpMethod httpMethod, HttpEntity<String> httpEntity, Class<T> t) {
		ResponseEntity<T> response = restTemplate.exchange(httpsURL, httpMethod,  httpEntity, t);
		return response.getBody();
	}
	
	private <T> T execute(String httpsURL, HttpMethod httpMethod, HttpEntity<String> httpEntity, Class<T> t, Map<String, String> params) {
		if (params == null || params.isEmpty())
			execute(httpsURL, httpMethod, httpEntity, t);
		ResponseEntity<T> response = restTemplate.exchange(httpsURL, httpMethod,  httpEntity, t, params);
		return response.getBody();
	}

	private String getUserId(){
		Properties props = propertyManager.loadProperties(CANADA_POST_SERVICE_PROPERTIES);
		return props.getProperty(PROPERTY_NAME_CANON_ID_IN_CANADA_POST_SERVICE + "_"  + propertyManager.loadEnvProperties());
	}
	
	private String getPassword(){
		Properties props = propertyManager.loadProperties(CANADA_POST_SERVICE_PROPERTIES);
		return props.getProperty(PROPERTY_NAME_PASSWORD_IN_CANADA_POST_SERVICE + "_"  + propertyManager.loadEnvProperties());
	}

	private String getServiceAddress(){
		Properties props = propertyManager.loadProperties(CANADA_POST_SERVICE_PROPERTIES);
		return props.getProperty(PROPERTY_NAME_CANADA_POST_SERVICE_ADDRES + "_"  + propertyManager.loadEnvProperties());
	}
	
	private String getCanonIdInCanadaPost(){
		Properties props = propertyManager.loadProperties(CANADA_POST_SERVICE_PROPERTIES);
		return props.getProperty(PROPERTY_NAME_CANON_ID_IN_CANADA_POST + "_"  + propertyManager.loadEnvProperties());
	}
	
	private String getContractIdInCanadaPost(){
		Properties props = propertyManager.loadProperties(CANADA_POST_SERVICE_PROPERTIES);
		return props.getProperty(PROPERTY_NAME_CONTRACT_ID_IN_CANADA_POST + "_"  + propertyManager.loadEnvProperties());
	}

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public PropertyManager getPropertyManager() {
		return propertyManager;
	}

	public void setPropertyManager(PropertyManager propertyManager) {
		this.propertyManager = propertyManager;
	}


}

package com.freeman.aci.model;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "autnresponse")
public class AutnResponse {
	
	private String action;
	private String response; 
	private ACIResponseData responseData;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	@XmlElement(name="responsedata")	
	public ACIResponseData getResponseData() {
		return responseData;
	}

	public void setResponseData(ACIResponseData responseData) {
		this.responseData = responseData;
	}
	
}

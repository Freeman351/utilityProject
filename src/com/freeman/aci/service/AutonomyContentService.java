package com.freeman.aci.service;

import com.freeman.aci.model.AutnResponse;


public interface AutonomyContentService extends java.io.Serializable {

	public String getAutonomyContents(String aciSearchURL);
	public AutnResponse getAutnResponse(String aciSearchURL);
	
}
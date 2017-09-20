package com.freeman.restful.spring.client.service;

public interface OceDriverDownloadService extends java.io.Serializable {

	public String getProductFamilies();
	public String getProductsByFamily(String famliyId);
	
}
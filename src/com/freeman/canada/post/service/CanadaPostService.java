package com.freeman.canada.post.service;

import com.freeman.canada.post.model.AuthorizedReturnRequest;


public interface CanadaPostService extends java.io.Serializable {

	public String getAuthorizedReturn(String language, String xmlString);
	public byte[] getArtifact(String language, String url);
	public String getOpenReturn(String language);
	public byte[] getReturnArtifact(String language, String artifactId);
	public byte[] geAuthorizedReturnDocument(String language, String xmlString) throws Exception ;
	public byte[] geAuthorizedReturnDocument(String language, AuthorizedReturnRequest authorizedReturnRequest) throws Exception;

}
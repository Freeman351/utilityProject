package com.freeman.canada.post.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="authorized-return-info", namespace="http://www.canadapost.ca/ws/authreturn-v2")
@XmlAccessorType(XmlAccessType.FIELD)
public class AuthorizedReturnResponse {
	
	@XmlElement(name="tracking-pin", namespace="http://www.canadapost.ca/ws/authreturn-v2")
	private String trackingPin;
	
	@XmlElement(name="links", namespace="http://www.canadapost.ca/ws/authreturn-v2")
	private AuthorizedReturnLinks authorizedReturnLinks;
	
	public String getTrackingPin() {
		return trackingPin;
	}

	public void setTrackingPin(String trackingPin) {
		this.trackingPin = trackingPin;
	}

	public AuthorizedReturnLinks getAuthorizedReturnLinks() {
		return authorizedReturnLinks;
	}

	public void setAuthorizedReturnLinks(AuthorizedReturnLinks authorizedReturnLinks) {
		this.authorizedReturnLinks = authorizedReturnLinks;
	}
	


}

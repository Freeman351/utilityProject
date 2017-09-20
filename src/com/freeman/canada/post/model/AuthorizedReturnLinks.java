package com.freeman.canada.post.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;


@XmlAccessorType(XmlAccessType.FIELD)
public class AuthorizedReturnLinks {
	
	@XmlElement(name="link", namespace="http://www.canadapost.ca/ws/authreturn-v2")	
	private List<AuthorizedReturnLink> authorizedReturnLink;

	public List<AuthorizedReturnLink> getAuthorizedReturnLink() {
		return authorizedReturnLink;
	}

	public void setAuthorizedReturnLink(
			List<AuthorizedReturnLink> authorizedReturnLink) {
		this.authorizedReturnLink = authorizedReturnLink;
	}
	
	
}

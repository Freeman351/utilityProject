package com.freeman.restful.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ProductFamily")
//@XmlAccessorType(XmlAccessType.FIELD)
public class ProductFamily {
	
	private String id;
	private String familyName;
	
	@XmlElement(name = "ID")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@XmlElement(name = "CommercialName")
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	
	
}

package com.freeman.canada.post.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="domestic-address")
@XmlAccessorType(XmlAccessType.FIELD)
public class DomesticAddress {
	
	@XmlElement(name="address-line-1")
	private String street;

	@XmlElement(name="address-line-2")
	private String suite;
	
	private String city;
	
	private String province;
	
	@XmlElement(name="postal-code")
	private String postalCode;
	
	
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getSuite() {
		return suite;
	}
	public void setSuite(String suite) {
		this.suite = suite;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}	
		
}


//infoBuffer.append("<domestic-address>");
//infoBuffer.append("<address-line-1>2701 Return Avenue</address-line-1>");
//infoBuffer.append("<city>Ottawa</city>");
//infoBuffer.append("<province>ON</province>");
//infoBuffer.append("<postal-code>K1A0B1</postal-code>");
//infoBuffer.append("</domestic-address>");

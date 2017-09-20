package com.freeman.canada.post.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ContactPerson {
	
	private String name;
	
	private String company;
	
	@XmlElement(name="domestic-address")
	private DomesticAddress address;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public DomesticAddress getAddress() {
		return address;
	}
	public void setAddress(DomesticAddress address) {
		this.address = address;
	}
		
}


//infoBuffer.append("<name>Jane Doe</name>");
//infoBuffer.append("<company>Capsule Corp.</company>");
//infoBuffer.append("<domestic-address>");
//infoBuffer.append("<address-line-1>2701 Return Avenue</address-line-1>");
//infoBuffer.append("<city>Ottawa</city>");
//infoBuffer.append("<province>ON</province>");
//infoBuffer.append("<postal-code>K1A0B1</postal-code>");
//infoBuffer.append("</domestic-address>");

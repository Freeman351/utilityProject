package com.freeman.canada.post.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="notification")
public class Notification {
	
	private String email;

	@XmlElement(name="on-shipment")
	private boolean onShipment;

	@XmlElement(name="on-exception")
	private boolean onException;

	@XmlElement(name="on-delivery")
	private boolean onDelivery;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isOnShipment() {
		return onShipment;
	}

	public void setOnShipment(boolean onShipment) {
		this.onShipment = onShipment;
	}

	public boolean isOnException() {
		return onException;
	}

	public void setOnException(boolean onException) {
		this.onException = onException;
	}

	public boolean isOnDelivery() {
		return onDelivery;
	}

	public void setOnDelivery(boolean onDelivery) {
		this.onDelivery = onDelivery;
	}
		
}

//<notification>
//	<email>hong_li@canon.canada.ca</email>
//	on-shipment>false</on-shipment>
//	<on-exception>true</on-exception>
//	<on-delivery>true</on-delivery>
//</notification>

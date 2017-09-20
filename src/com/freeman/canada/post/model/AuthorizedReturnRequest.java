package com.freeman.canada.post.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="authorized-return", namespace="http://www.canadapost.ca/ws/authreturn-v2")
@XmlAccessorType(XmlAccessType.FIELD)
public class AuthorizedReturnRequest {
	
	@XmlElement(name="service-code", namespace="http://www.canadapost.ca/ws/authreturn-v2")
	private String serviceCode;
	
	private Returner returner;
	
	private Receiver receiver;

	@XmlElement(name="parcel-characteristics", namespace="http://www.canadapost.ca/ws/authreturn-v2")
	private ParcelCharacteristics parcelCharacteristics;

	@XmlElement(name="print-preferences", namespace="http://www.canadapost.ca/ws/authreturn-v2")
	private PrintPreferences printPreferences;
	
	@XmlElement(name="settlement-info", namespace="http://www.canadapost.ca/ws/authreturn-v2")
	private String settlement;
	
	private Notifications notifications;

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public Returner getReturner() {
		return returner;
	}

	public void setReturner(Returner returner) {
		this.returner = returner;
	}

	public Receiver getReceiver() {
		return receiver;
	}

	public void setReceiver(Receiver receiver) {
		this.receiver = receiver;
	}

	public ParcelCharacteristics getParcelCharacteristics() {
		return parcelCharacteristics;
	}

	public void setParcelCharacteristics(ParcelCharacteristics parcelCharacteristics) {
		this.parcelCharacteristics = parcelCharacteristics;
	}

	public PrintPreferences getPrintPreferences() {
		return printPreferences;
	}

	public void setPrintPreferences(PrintPreferences printPreferences) {
		this.printPreferences = printPreferences;
	}

	public String getSettlement() {
		return settlement;
	}

	public void setSettlement(String settlement) {
		this.settlement = settlement;
	}

	public Notifications getNotifications() {
		return notifications;
	}

	public void setNotifications(Notifications notifications) {
		this.notifications = notifications;
	}

	
}

//infoBuffer.append("<authorized-return xmlns=\"http://www.canadapost.ca/ws/authreturn-v2\">");
//infoBuffer.append("<service-code>DOM.EP</service-code>");
//infoBuffer.append("<returner>");
//infoBuffer.append("<name>Jane Doe</name>");
//infoBuffer.append("<company>Capsule Corp.</company>");
//infoBuffer.append("<domestic-address>");
//infoBuffer.append("<address-line-1>2701 Return Avenue</address-line-1>");
//infoBuffer.append("<city>Ottawa</city>");
//infoBuffer.append("<province>ON</province>");
//infoBuffer.append("<postal-code>K1A0B1</postal-code>");
//infoBuffer.append("</domestic-address>");
//infoBuffer.append("</returner>");
//infoBuffer.append("<receiver>");
//infoBuffer.append("<name>Andrea Chmielinski</name>");
//infoBuffer.append("<company>Canon canada Inc.</company>");
//infoBuffer.append("<domestic-address>");
//infoBuffer.append("<address-line-1>6390 Dixie Rd</address-line-1>");
//infoBuffer.append("<city>Mississauga</city>");
//infoBuffer.append("<province>ON</province>");
//infoBuffer.append("<postal-code>L5T1P7</postal-code>");
//infoBuffer.append("</domestic-address>");
//infoBuffer.append("</receiver>");
//infoBuffer.append(" <parcel-characteristics><weight>15</weight></parcel-characteristics>");
//infoBuffer.append("<print-preferences><output-format>8.5x11</output-format></print-preferences>");
//infoBuffer.append("<settlement-info></settlement-info>");
//infoBuffer.append("<notifications>");
//infoBuffer.append("<notification>");
//infoBuffer.append("<email>hong_li@canon.canada.ca</email>");
//infoBuffer.append("<on-shipment>false</on-shipment>");
//infoBuffer.append("<on-exception>true</on-exception>");
//infoBuffer.append("<on-delivery>true</on-delivery>");
//infoBuffer.append("</notification>");
//infoBuffer.append("</notifications>");		
//infoBuffer.append("</authorized-return >");

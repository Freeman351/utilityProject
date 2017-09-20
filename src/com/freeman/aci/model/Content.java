package com.freeman.aci.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="content", namespace="http://schemas.autonomy.com/aci/")
public class Content {
	

	private Document document;

	@XmlElement(name="DOCUMENT")
	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}
	
		
}

package com.freeman.aci.model;

import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlPath;

@XmlRootElement(name="DOCUMENT")
public class Document2 {
	
	@XmlPath("DESCRIPTION/text()")
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
		
}

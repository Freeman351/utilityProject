package com.freeman.aci.model;

import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlPath;

@XmlRootElement(name="content", namespace="http://schemas.autonomy.com/aci/")
public class Content2 {
	
	@XmlPath("DOCUMENT/DESCRIPTION/text()")
	private String description;

	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}

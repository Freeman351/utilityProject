package com.freeman.canada.post.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="parcel-characteristics")
public class ParcelCharacteristics {
	
	private String weight;
	
	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

		
}


//infoBuffer.append(" <parcel-characteristics><weight>15</weight></parcel-characteristics>");

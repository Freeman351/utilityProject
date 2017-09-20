package com.freeman.aci.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="hit", namespace="http://schemas.autonomy.com/aci/")
public class HitData {
	
	private String reference;
	private String id;
	private String section;
	private String title;
	private Content content;
	
	@XmlElement(namespace="http://schemas.autonomy.com/aci/")	
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}

	@XmlElement(namespace="http://schemas.autonomy.com/aci/")	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	@XmlElement(namespace="http://schemas.autonomy.com/aci/")	
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}

	@XmlElement(namespace="http://schemas.autonomy.com/aci/")	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	@XmlElement(name="content", namespace="http://schemas.autonomy.com/aci/")
	public Content getContent() {
		return content;
	}
	public void setContent(Content content) {
		this.content = content;
	}
		
}


//	<autn:hit>
//		<autn:reference>/inetCA/products?m=gp&amp;pid=24236</autn:reference>
//		<autn:id>70054</autn:id>
//		<autn:section>7</autn:section>
//		<autn:weight>82.34</autn:weight>
//		<autn:links>EOS</autn:links>
//		<autn:database>iNetCA_ConsumerEng</autn:database>
//		<autn:title>EOS Rebel T5i with EF-S 18-55mm IS STM Kit - Canon Canada Inc.</autn:title>
//		<autn:content>
//			<DOCUMENT>
//				<DESCRIPTION>
//						Consumer &amp; Home OfficeCamerasEOS CamerasEOS Rebel T5i with EF-S 18-55mm IS STM Kit
//				</DESCRIPTION>
//			</DOCUMENT>
//		</autn:content>
//	</autn:hit>
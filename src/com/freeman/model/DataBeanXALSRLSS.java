package com.freeman.model;

public class DataBeanXALSRLSS {
	
	private String itemDescription;
	private String itemCode;
	private String licenseAccessNumber;

	public DataBeanXALSRLSS(){		
	}

	public DataBeanXALSRLSS(String itemDescription, String itemCode, String licenseAccessNumber) {
		super();
		this.itemDescription = itemDescription;
		this.itemCode = itemCode;
		this.licenseAccessNumber = licenseAccessNumber;
	}

	public String getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getLicenseAccessNumber() {
		return licenseAccessNumber;
	}

	public void setLicenseAccessNumber(String licenseAccessNumber) {
		this.licenseAccessNumber = licenseAccessNumber;
	}
	
}

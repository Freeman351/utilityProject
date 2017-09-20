package com.freeman.restful.model;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ArrayOfProductFamily")
public class ProductFamilyGroup {
	
	private ArrayList<ProductFamily> productFamilyList;

	@XmlElement(name = "ProductFamily")
	public ArrayList<ProductFamily> getProductFamilyList() {
		return productFamilyList;
	}

	public void setProductFamilyList(ArrayList<ProductFamily> productFamilyList) {
		this.productFamilyList = productFamilyList;
	}

	
}

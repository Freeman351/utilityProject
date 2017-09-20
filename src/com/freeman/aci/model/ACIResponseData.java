package com.freeman.aci.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;


public class ACIResponseData {
	
	private long numhits;
	private long totalhits;
	private List<HitData> hitDatas;
	
	@XmlElement(namespace="http://schemas.autonomy.com/aci/")	
	public long getNumhits() {
		return numhits;
	}
	public void setNumhits(long numhits) {
		this.numhits = numhits;
	}
	
	@XmlElement(namespace="http://schemas.autonomy.com/aci/")	
	public long getTotalhits() {
		return totalhits;
	}
	public void setTotalhits(long totalhits) {
		this.totalhits = totalhits;
	}
	@XmlElement(name="hit", namespace="http://schemas.autonomy.com/aci/")	
	public List<HitData> getHitDatas() {
		return hitDatas;
	}
	public void setHitDatas(List<HitData> hitDatas) {
		this.hitDatas = hitDatas;
	}
	
}

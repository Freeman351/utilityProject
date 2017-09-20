package com.freeman.printfleet.model;

public class DeviceSummary {

	private String id;
	private CommonLifecountMeters commonLifecountMeters;
	private NodeInfo name;
	private NodeInfo serialNumber;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public NodeInfo getName() {
		return name;
	}
	
	public CommonLifecountMeters getCommonLifecountMeters() {
		return commonLifecountMeters;
	}
	
	public void setCommonLifecountMeters(CommonLifecountMeters commonLifecountMeters) {
		this.commonLifecountMeters = commonLifecountMeters;
	}
	
	public void setName(NodeInfo name) {
		this.name = name;
	}
	
	public NodeInfo getSerialNumber() {
		return serialNumber;
	}
	
	public void setSerialNumber(NodeInfo serialNumber) {
		this.serialNumber = serialNumber;
	}
	
}

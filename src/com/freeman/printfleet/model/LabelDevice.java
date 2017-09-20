package com.freeman.printfleet.model;

public class LabelDevice {

	private String deviceId;
	private long count;
	private String firstReportedAt;
	private String lastReportedAt;
	
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public String getFirstReportedAt() {
		return firstReportedAt;
	}
	public void setFirstReportedAt(String firstReportedAt) {
		this.firstReportedAt = firstReportedAt;
	}
	public String getLastReportedAt() {
		return lastReportedAt;
	}
	public void setLastReportedAt(String lastReportedAt) {
		this.lastReportedAt = lastReportedAt;
	}

}

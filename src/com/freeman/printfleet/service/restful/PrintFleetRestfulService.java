package com.freeman.printfleet.service.restful;


public interface PrintFleetRestfulService extends java.io.Serializable {

	public String getUserByCredentials(String userName, String password);
	public String getLabelsByGroupId(String groupId, String userName, String password);
	public String getDevicesByLable(String groupId, String label, String startDate, String endDate, String userName, String password);
	public String getDeviceAttributeByDeviceId(String deviceId, String attributeName, String userName, String password);
	public String getDevicesByGroupId(String groupId, String userName, String password);
	public String getSummaryOfDeviceByDeviceId(String deviceId, String userName, String password);
	

}
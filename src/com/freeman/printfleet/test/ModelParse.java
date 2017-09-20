package com.freeman.printfleet.test;

import java.util.List;

import junit.framework.TestCase;

import com.freeman.printfleet.model.Attribute;
import com.freeman.printfleet.model.Device;
import com.freeman.printfleet.model.DeviceAttribute;
import com.freeman.printfleet.model.DeviceSummary;
import com.freeman.printfleet.model.Label;
import com.freeman.printfleet.model.LabelDevice;
import com.freeman.printfleet.utilities.GsonUtil;

public class ModelParse extends TestCase{
	
	public void testJson2Label(){
		Label label = (Label) GsonUtil.json2Obj(getJsonString4Labl(), Label.class);
		System.out.println("label is =" + label.getLabel()); 
	}
	
	
	public void testJson2LabelArray(){
		Label[] labels =  GsonUtil.getArrayFromJson(getJsonString4Labels(), Label[].class);
		System.out.println("labels size is =" + labels.length); 
	}
	
	public void testJson2LabelList(){
		List<Label> labels =  GsonUtil.getListFromJsonArray(getJsonString4Labels(), Label[].class);
		System.out.println("labels size is =" + labels.size()); 
	}
	
	public void testJson2LabelDevice(){
		LabelDevice labelDevice = (LabelDevice) GsonUtil.json2Obj(getJsonString4LabelDevice(), LabelDevice.class);
		System.out.println("label device is =" + labelDevice.getCount()); 
	}

	public void testJson2LabelDeviceArray(){
		LabelDevice[] labelDevices = GsonUtil.getArrayFromJson(getJsonString4LabelDevices(), LabelDevice[].class);
		System.out.println("label devices size =" + labelDevices.length); 
	}
	
	public void testJson2LabelDeviceList(){
		List<LabelDevice> labelDevices = GsonUtil.getListFromJsonArray(getJsonString4LabelDevices(), LabelDevice[].class);
		System.out.println("label devices is =" + labelDevices.size()); 
	}
	
	public void testJson2Attribute(){
		Attribute attribute = (Attribute) GsonUtil.json2Obj(getJsonString4Attribute(), Attribute.class);
		System.out.println("attribute is =" + attribute.getName() + "=" + attribute.getValue()); 
	}
	
	public void testJson2DeviceAttribute(){
		DeviceAttribute deviceAttribute = (DeviceAttribute) GsonUtil.json2Obj(getJsonString4DeviceAttribute(), DeviceAttribute.class);
		System.out.println("deviceAttribute is =" + deviceAttribute.getAttribute().getName() + "=" + deviceAttribute.getAttribute().getValue()); 
	}

	public void testJson2Device(){
		Device device = (Device) GsonUtil.json2Obj(getJsonString4Device(), Device.class);
		System.out.println("device is =" + device.getName() + "=" + device.getSerialNumber()); 
	}

	public void testJson2DeviceArray(){
		Device[] devices = GsonUtil.getArrayFromJson(getJsonString4Devices(), Device[].class);
		System.out.println("devices size =" + devices.length); 
	}

	public void testJson2DeviceSummary(){
		DeviceSummary deviceSummary = (DeviceSummary) GsonUtil.json2Obj(getJsonString4DeviceSummary(), DeviceSummary.class);
		System.out.println("device summary is =" + deviceSummary.getName().getValue() 
				+ "=" + deviceSummary.getSerialNumber().getValue()
				+ "=" + deviceSummary.getCommonLifecountMeters().getTotal().getCount()); 
	}
	
	
	/*-----test data----------------------*/
	
	static String getJsonString4Labl(){
		return "{\"label\": \"CANON_101\", \"ab\": \"a\"}";
	}

	static String getJsonString4Labels(){
		return "[{\"label\": \"CANON_101\"}, {\"label\": \"CANON_102\"}, {\"label\": \"CANON_103\"}]";
	}

	static String getJsonString4LabelDevice(){
		return "{"
				+ "\"deviceId\": \"dbba1d71-b9cf-44b2-8e1a-0fd16f0e60a3\"," 
				+ "\"delta\": 1012," 
				+ "\"count\": 7552,"
				+ "\"firstReportedAt\": \"2013-07-16T14:58:18Z\"," 
				+ "\"lastReportedAt\": \"2014-03-14T15:17:23Z\""
				+ "}";
	}
	
	static String getJsonString4LabelDevices(){
		return "[" 
				+"{"
				+ "\"deviceId\": \"dbba1d71-b9cf-44b2-8e1a-0fd16f0e60a2\"," 
				+ "\"delta\": 1012," 
				+ "\"count\": 7552,"
				+ "\"firstReportedAt\": \"2013-07-12T14:58:18Z\"," 
				+ "\"lastReportedAt\": \"2014-03-12T15:17:23Z\""
				+ "}"
				+ "," 
				+"{"
				+ "\"deviceId\": \"dbba1d71-b9cf-44b2-8e1a-0fd16f0e60a4\"," 
				+ "\"delta\": 1014," 
				+ "\"count\": 7554,"
				+ "\"firstReportedAt\": \"2013-07-14T14:58:18Z\"," 
				+ "\"lastReportedAt\": \"2014-03-14T15:17:23Z\""
				+ "}" 
				+"]";
	}

	static String getJsonString4Attribute(){
		return "{"
			    + "\"id\": \"1410d161-f58d-4938-af38-190b1cd8c0f1\","
			    + "\"name\": \"SERIALNUMBER\","
			    + "\"attributeName\": \"SERIALNUMBER\","
			    + "\"value\": \"FAH00505\","
			    + "\"lastUpdatedAt\": \"2013-07-16T14:58:18Z\""
				+ "}";
	}

	static String getJsonString4DeviceAttribute(){
		return "{"
				+ "\"attribute\": {"
			    + "\"id\": \"1410d161-f58d-4938-af38-190b1cd8c0f1\","
			    + "\"name\": \"SERIALNUMBER\","
			    + "\"attributeName\": \"SERIALNUMBER\","
			    + "\"value\": \"FAH00505\","
			    + "\"lastUpdatedAt\": \"2013-07-16T14:58:18Z\""
			    + "}"
				+ "}";
	}

	static String getJsonString4Device(){
		return "{"
			    + "\"groupId\": \"a8bd97c5-dbce-410c-bdbb-9685d6e80028\","
			    + "\"lastReportedAt\": \"2014-04-15T17:44:52Z\","
			    + "\"serialNumber\": \"DUK00011\","
			    + "\"modelMatch\": {"
			    + "\"type\": \"AutoNone\","
			    + "\"modifiedAt\": \"2014-10-02T17:53:40.04Z\""
			    + "},"
			    + "\"id\": \"4084e252-166c-4e86-9493-13e73e1c1be0\","
			    + "\"name\": \"Canon iPR1125-J200 1.1\""
				+ "}";
	}

	static String getJsonString4Devices(){
		return "[" 
				+ "{"
			    + "\"groupId\": \"a8bd97c5-dbce-410c-bdbb-9685d6e80028\","
			    + "\"lastReportedAt\": \"2014-04-15T17:44:52Z\","
			    + "\"serialNumber\": \"DUK00011\","
			    + "\"modelMatch\": {"
			    + "\"type\": \"AutoNone\","
			    + "\"modifiedAt\": \"2014-10-02T17:53:40.04Z\""
			    + "},"
			    + "\"id\": \"4084e252-166c-4e86-9493-13e73e1c1be0\","
			    + "\"name\": \"Canon iPR1125-J200 1.1\""
				+ "}"
			    + ","
				+ "{"
			    + "\"groupId\": \"a8bd97c5-dbce-410c-bdbb-9685d6e80028\","
			    + "\"lastReportedAt\": \"2014-04-15T17:44:52Z\","
			    + "\"serialNumber\": \"DUK00012\","
			    + "\"modelMatch\": {"
			    + "\"type\": \"AutoNone\","
			    + "\"modifiedAt\": \"2014-10-02T17:53:40.04Z\""
			    + "},"
			    + "\"id\": \"4084e252-166c-4e86-9493-13e73e1c1be2\","
			    + "\"name\": \"Canon iPR1125-J200 1.2\""
				+ "}"
			    + "]";
	}

	static String getJsonString4DeviceSummary(){
		return "{"
				+ "\"id\": \"dbba1d71-b9cf-44b2-8e1a-0fd16f0e60a3\","
				+ "\"commonLifecountMeters\": {"
				+ "\"total\": {"
				+ "\"count\": 7552,"
				+ "\"delta\": 0,"
				+ "\"sparkline\": [0,0,0],"
				+ "\"firstReportedAt\": \"2013-07-16T14:58:18Z\","
				+ "\"lastReportedAt\": \"2014-03-14T15:17:23Z\"}},"
				+ "\"name\": {"
				+ "\"value\": \"Canon iR-ADV C2020 33.01\","
				+ "\"deviceValue\": \"Canon iR-ADV C2020 33.01\","
				+ "\"source\": \"Device\"},"
				+ "\"serialNumber\": {"
				+ "\"value\": \"FAH00505\","
				+ "\"deviceValue\": \"FAH00505\","
				+ "\"source\": \"Device\"},"
				+  "\"nameFromTemplate\": \"Canon iR-ADV C2020 33.01\""
				+ "}";
	}

}

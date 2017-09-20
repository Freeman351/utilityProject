package com.freeman.batch.quartz;

import org.apache.log4j.Logger;

import com.freeman.j2ee.service.CCI21Service;
import com.opensymphony.xwork2.ActionSupport;


public class SyncJobAction extends ActionSupport {
	private static Logger logger = Logger.getLogger(SyncJobAction.class);
	private CCI21Service service;

	public String load() {
		DataSyncJob job = new DataSyncJob();
		job.setService(service);
		try {
			job.execute();
		} catch (Throwable e) {
			logger.error(e);
		}
		return "message";
	}


}

package com.freeman.batch.quartz;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionException;

import com.freeman.j2ee.service.CCI21Service;

public class DataSyncJob {
	private static Logger log = Logger.getLogger(DataSyncJob.class);
	private CCI21Service service;

	public DataSyncJob() {
		super();
	}

	public void execute() throws Throwable {
		try {
			log.debug("Start batch job");
		} catch (RuntimeException e) {
			throw new JobExecutionException(e);
		} catch (Exception e) {
			throw new JobExecutionException(e);
		} finally{
			log.debug("End batch job");			
		}
	}

	public CCI21Service getService() {
		return service;
	}

	public void setService(CCI21Service service) {
		this.service = service;
	}

}

package com.freeman.payment.moneris.service;

import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import MpiAPI.Acs;
import MpiAPI.HttpsPostMpiRequest;
import MpiAPI.MpiResponse;
import MpiAPI.Txn;

import com.freeman.exception.BaseException;
import com.freeman.utilities.PropertyManager;


public class MonerisVBVPaymentServiceImpl implements MonerisVBVPaymentService {
	
	protected static Logger logger = Logger.getLogger(MonerisVBVPaymentServiceImpl.class);	
	public static final String ORDER_SUFFIX = "-";  //used by baseBusinessAction
	
	protected static String host = "";
	protected static String storeId = "";
	protected static String apiToken = "";
	protected static String crypt = "";
	protected static String merchantVbvUrl = "";

	static  {
		MonerisVBVPaymentServiceImpl.init();						// Load-time initialization, once.
	}
	
	@Override
	public MpiResponse mpiTxn(String merchantUrl, String orderId, String suffix, double totalAmount, String cardNum, 
			String expiration, String agentHeader, String acceptHeader) throws BaseException {

		//xid number must be 20 char
		String xid =  orderId + suffix;
		// call util to fill with zeros up to 20 chars
		if (xid.length()<20)
			xid = StringUtils.rightPad(xid, 20, '0');
		
		xid = xid.substring(0,20);
		
		Txn txn = new Txn();
		txn.setXid(xid);
		txn.setAmount(String.valueOf(totalAmount));
		txn.setCardNumber(cardNum);
		txn.setExpDate(expiration);
		txn.setMD(genMD(xid, cardNum, expiration,String.valueOf(String.valueOf(totalAmount))));
		txn.setMerchantUrl(merchantUrl + merchantVbvUrl);
		txn.setHttpAccept(agentHeader);
		txn.setHttpUserAgent(agentHeader);
		HttpsPostMpiRequest mpiReq = new HttpsPostMpiRequest(host, storeId, apiToken, txn);
		MpiResponse mpiRes = mpiReq.getResponse();
		return mpiRes;
	}


	@Override
	public MpiResponse mpiAcs(String orderId, String suffix, String acsPaRes,
			String acsMD) throws BaseException {
		Acs acs = new Acs();
		acs.setPaRes(acsPaRes);
		acs.setMD(acsMD);

        HttpsPostMpiRequest mpiReq = new HttpsPostMpiRequest(host, storeId, apiToken, acs);
		MpiResponse mpiRes = mpiReq.getResponse();
		
		return mpiRes;
	}

	private String genMD(String xid, String pan, String expdate, String amount){		
		return new String(
				"xid=" + xid
				+ "&pan=" + pan
				+ "&expdate=" + expdate
				+ "&amount=" + amount
				);
	}
		
	/**
	 * Perform initialization of Moneries properties.
	 */
	private static void init() {
		PropertyManager propertyManager = new PropertyManager();
		Properties prop = propertyManager.loadMonerisProperties();

		host = prop.getProperty("host");
		storeId = prop.getProperty("store_id");
		apiToken = prop.getProperty("api_token");
		crypt = prop.getProperty("crypt");
		merchantVbvUrl = prop.getProperty("merchantVbvUrl");
		
		logger.info("Performed initialization of MoneriesPaymentService: host = " + host + ", storeId = " 
				+ storeId + ", apiToken = " + apiToken + ", crypt = " + crypt + ", merchantVbvUrl = " + merchantVbvUrl + ".");
	}
	
	
}

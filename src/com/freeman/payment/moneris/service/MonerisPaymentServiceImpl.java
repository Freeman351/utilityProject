package com.freeman.payment.moneris.service;

import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import JavaAPI.AvsInfo;
import JavaAPI.CavvPreAuth;
import JavaAPI.CavvPurchase;
import JavaAPI.Completion;
import JavaAPI.CvdInfo;
import JavaAPI.HttpsPostRequest;
import JavaAPI.PreAuth;
import JavaAPI.Purchase;
import JavaAPI.PurchaseCorrection;
import JavaAPI.Receipt;
import JavaAPI.Refund;

import com.freeman.exception.BaseException;
import com.freeman.utilities.PropertyManager;

/**
 * @author BDu
 * 
 */
public class MonerisPaymentServiceImpl implements MonerisPaymentService {
	protected static Logger logger = Logger.getLogger(MonerisPaymentServiceImpl.class);

	protected static String host = "";
	protected static String storeId = "";
	protected static String apiToken = "";
	protected static String crypt = "";
	
	protected static final int MONERIS_LOWEST_OK_CODE = 0;
	protected static final int MONERIS_LOWEST_DECLINE_CODE = 50;
	
	private static Map<Object, Object> monerisCodes_en = null;	// Injected upon initialization via Spring (Ioc).
	private static Map<Object, Object> monerisCodes_fr = null;	// Injected upon initialization via Spring (Ioc).
	
	static  {
		MonerisPaymentServiceImpl.init();						// Load-time initialization, once.
	}

	/**
	 * 
	 */
	public MonerisPaymentServiceImpl() {
		// Initialization is done once, upon object load.
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.canon.payment.service.MonerisPaymentService#preAuth(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, JavaAPI.AvsInfo,
	 * JavaAPI.CvdInfo)
	 */
	public Receipt preAuth(String orderId, double totalAmount, String cardNum,
			String expiration, AvsInfo avs, CvdInfo cvd) throws BaseException{
		return preAuth(orderId, totalAmount, cardNum, expiration, avs, cvd, this.crypt);
	}
	public Receipt preAuth(String orderId, double totalAmount, String cardNum,
			String expiration, AvsInfo avs, CvdInfo cvd, String updatedCrypt) 
			throws BaseException {
		
		if(totalAmount <= 0) {
			StringBuffer b = new StringBuffer();
			b.append("Trying to pre auth invalid amount: ").append(totalAmount)
			 .append(", order: ").append(orderId);
			
			String errorMsg = b.toString();
			logger.error(errorMsg);
			
			throw new BaseException(errorMsg);
		}
	
		PreAuth preAuth = 
			new PreAuth(
					orderId, 
					String.valueOf(totalAmount), 
					cardNum,
					expiration, 
					updatedCrypt);

		preAuth.setAvsInfo(avs);
		preAuth.setCvdInfo(cvd);
		
		HttpsPostRequest mpgReq = new HttpsPostRequest(host, storeId, apiToken,
				preAuth);
		try {
			return mpgReq.getReceipt();
		} catch (Exception e) {
			logger.error(e);
			throw new BaseException(e);
		}
	}

	//TODO - <AP> need to pass AvsInfo avs, CvdInfo cvd
	public Receipt cavvPreAuth(String orderId, double totalAmount, String cardNum,
			String expiration, String cavv, AvsInfo avs, CvdInfo cvd) throws BaseException 
	{
		if(totalAmount <= 0) {
			StringBuffer b = new StringBuffer();
			b.append("Trying to pre auth invalid amount: ").append(totalAmount)
			 .append(", order: ").append(orderId);
			
			String errorMsg = b.toString();
			logger.error(errorMsg);
			
			throw new BaseException(errorMsg);
		}
	
		CavvPreAuth cavvPreAuth = 
			new CavvPreAuth(
					orderId, 
					String.valueOf(totalAmount), 
					cardNum,
					expiration, 
					cavv);
		//add Address and CVD verification
		cavvPreAuth.setAvsInfo(avs);
		cavvPreAuth.setCvdInfo(cvd);
		
		HttpsPostRequest mpgReq = new HttpsPostRequest(host, storeId, apiToken, cavvPreAuth);
		try {
			Receipt receipt = mpgReq.getReceipt();  
			return receipt;
		} catch (Exception e) {
			logger.error(e);
			throw new BaseException(e);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.canon.payment.service.MonerisPaymentService#capture(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public Receipt capture(String orderId, double amount, String txnNumber)
			throws BaseException {
		return capture(orderId, amount, txnNumber, crypt);
		
	}
	
	public Receipt capture(String orderId, double amount, String txnNumber, String updatedCrypt)
			throws BaseException 
	{
		if(amount <= 0) {
			StringBuffer b = new StringBuffer();
			b.append("Trying to capture invalid amount: ").append(amount)
			 .append(", order: ").append(orderId);
			
			String errorMsg = b.toString();
			logger.error(errorMsg);
			
			throw new BaseException(errorMsg);
		}
		
		Completion cardInfo = 
			new Completion(
				orderId, 
				String.valueOf(amount), 
				txnNumber,
				//TODO - <AP> should use same crypt_type which was used in preauth "6" or "7" 
				updatedCrypt);
		HttpsPostRequest mpgReq = new HttpsPostRequest(host, storeId, apiToken,
				cardInfo);
		try {
			return mpgReq.getReceipt();
		} catch (Exception e) {
			logger.error(e);
			throw new BaseException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.canon.payment.service.MonerisPaymentService#purchase(JavaAPI.Purchase)
	 */
	public Receipt purchase(String orderId, double totalAmount, String cardNum,
			String expiration, AvsInfo avs, CvdInfo cvd) 
			throws BaseException {
		
		return purchase(orderId, totalAmount, cardNum, expiration, avs, cvd, crypt);
	}

	public Receipt purchase(String orderId, double totalAmount, String cardNum,
			String expiration, AvsInfo avs, CvdInfo cvd, String updatedCrypt) throws BaseException 
	{
		if(totalAmount <= 0) {
			StringBuffer b = new StringBuffer();
			b.append("Trying to authorize invalid amount (purchase): ").append(totalAmount)
			 .append(", order: ").append(orderId);
			
			String errorMsg = b.toString();
			logger.error(errorMsg);
			
			throw new BaseException(errorMsg);
		}
		
		Purchase cardInfo = 
			new Purchase(
				orderId, 
				"canon_estore", 
				String.valueOf(totalAmount),
				cardNum, 
				expiration, 
				updatedCrypt);
		cardInfo.setAvsInfo(avs);
		cardInfo.setCvdInfo(cvd);
		HttpsPostRequest mpgReq = new HttpsPostRequest(host, storeId, apiToken,
				cardInfo);
		try {
			return mpgReq.getReceipt();
		} catch (Exception e) {
			logger.error(e);
			throw new BaseException(e);
		}
	}

	public Receipt cavvPurchase(String orderId, double totalAmount, String cardNum,
			String expiration, String cavv) throws BaseException {
		
		if(totalAmount <= 0) {
			StringBuffer b = new StringBuffer();
			b.append("Trying to authorize invalid amount (purchase): ").append(totalAmount)
			 .append(", order: ").append(orderId);
			
			String errorMsg = b.toString();
			logger.error(errorMsg);
			
			throw new BaseException(errorMsg);
		}
		
		CavvPurchase cavvPurchase = new CavvPurchase (
				orderId,
				String.valueOf(totalAmount),
				cardNum,
				expiration,
				cavv
			); 

		HttpsPostRequest mpgReq = new HttpsPostRequest(host, storeId, apiToken, cavvPurchase);

		try {
			return mpgReq.getReceipt();
		} catch (Exception e) {
			logger.error(e);
			throw new BaseException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.canon.payment.service.MonerisPaymentService#voidTransaction(java.lang
	 * .String, java.lang.String)
	 */
	public Receipt voidTransaction(String orderId, String txnNumber)
			throws BaseException {
		
		return voidTransaction(orderId, txnNumber, crypt);
	}
	public Receipt voidTransaction(String orderId, String txnNumber, String updatedCrypt)
			throws BaseException {
		PurchaseCorrection cardInfo = new PurchaseCorrection(orderId,
				txnNumber, updatedCrypt);
		HttpsPostRequest mpgReq = new HttpsPostRequest(host, storeId, apiToken,
				cardInfo);
		try {
			return mpgReq.getReceipt();
		} catch (Exception e) {
			logger.error(e);
			throw new BaseException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.canon.payment.service.MonerisPaymentService#refund(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */

	public Receipt refund(String orderId, double amount, String txnNumber)
		throws BaseException {
		
		return refund(orderId, amount, txnNumber, crypt);
	}
	
	public Receipt refund(String orderId, double amount, String txnNumber, String updatedCrypt)
			throws BaseException 
	{
		if(amount <= 0) {
			StringBuffer b = new StringBuffer();
			b.append("Trying to refund invalid amount: ").append(amount)
			 .append(", order: ").append(orderId);
			
			String errorMsg = b.toString();
			logger.error(errorMsg);
			
			throw new BaseException(errorMsg);
		}
		
		Refund cardInfo = new Refund(orderId, String.valueOf(amount), txnNumber, updatedCrypt);
		HttpsPostRequest mpgReq = new HttpsPostRequest(host, storeId, apiToken,
				cardInfo);
		try {
			return mpgReq.getReceipt();
		} catch (Exception e) {
			logger.error(e);
			throw new BaseException(e);
		}
	}

	/**
	 * Perform initialization of Moneries properties.
	 */
	private static void init() {
		Properties prop = new PropertyManager().loadMonerisProperties();

		host = prop.getProperty("host");
		storeId = prop.getProperty("store_id");
		apiToken = prop.getProperty("api_token");
		crypt = prop.getProperty("crypt");
		
		logger.info("Performed initialization of MoneriesPaymentService: host = " + host + ", storeId = " 
				+ storeId + ", apiToken = " + apiToken + ", crypt = " + crypt + ".");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.canon.payment.service.MonerisPaymentService#isApproved(java.lang.String
	 * )
	 */
	public boolean isApproved(String respCode) {
		try {
			
						
			int response = Integer.parseInt(respCode);
			
			//check null condition
			if(respCode == null){
				logger.info("Moneris response code is " + response + ", which means: this is probably a duplicate order number" );
				return false;
			}
						
			logger.info("Moneris response code is " + response + ", which means: " + 
								MonerisPaymentServiceImpl.getResponseCodeMsg_en(respCode));
						
			if (response >= MONERIS_LOWEST_OK_CODE && response < MONERIS_LOWEST_DECLINE_CODE) {
				// Approved
				return true;
			} else {
				// Declined
				return false;
			}
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}
	
	
	@Autowired(required = true)
	public static Map<Object, Object> getMonerisCodes_en() {
		return monerisCodes_en;
	}

	@Autowired(required = true)
	public static void setMonerisCodes_en(Map<Object, Object> monerisCodes_en) {
		logger.info("Setting moneriesCodes_en to: " + monerisCodes_en);
		MonerisPaymentServiceImpl.monerisCodes_en = monerisCodes_en;
	}
	
	@Autowired(required = true)
	public static Map<Object, Object> getMonerisCodes_fr() {
		return monerisCodes_fr;
	}

	@Autowired(required = true)
	public static void setMonerisCodes_fr(Map<Object, Object> monerisCodes_fr) {
		logger.info("Setting moneriesCodes_fr to: " + monerisCodes_fr);
		MonerisPaymentServiceImpl.monerisCodes_fr = monerisCodes_fr;
	}
	
	/**
	 * Obtain response code message for Moneris response code.
	 * Prepand 0 and 00 to handle leading zero cases.
	 * 
	 * @param monerisCodes -- EN/FR map of response codes and their messages.
	 * @param respCode -- response code from Moneris.
	 * 
	 * @return Response code message, or "" if none found.
	 */
	public static String getResponseCodeMsg(Map monerisCodes, String respCode) {
		String msg = null;
		
		if (monerisCodes != null && respCode != null) {
			try {
				msg = (String) monerisCodes.get((Object) respCode);
				//
				// First, take care of responses starting with zero, like 086, that are stored as key 86.
				//
				if (msg == null) {
					if (respCode.startsWith("0") && respCode.length() > 1) {
						msg = getResponseCodeMsg(monerisCodes, respCode.substring(1));
					}
				}
				
				//
				// Take care of responses without leading zeros, but that are stored as keys 087 or 0096.
				//
				if (msg == null) {
					msg =(String) monerisCodes.get((Object) ("0" + respCode));
					
					if (msg == null) {
						msg =(String) monerisCodes.get((Object) ("00" + respCode));
					}
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (msg == null) {
			msg = "";
		}
		logger.info("Lookup of Moneris reponse codes for " + respCode + " yields: " + msg);
		return msg;
	}
	
	/**
	 * Obtain response code EN message for Moneris response code.
	 * 
	 * @param respCode -- response code from Moneris.
	 * 
	 * @return Response code message, or "" if none found.
	 */
	public static String getResponseCodeMsg_en(String respCode) {
		return (getResponseCodeMsg(monerisCodes_en, respCode.trim()));
	}
	
	/**
	 * Obtain response code FR message for Moneris response code.
	 * 
	 * @param respCode -- response code from Moneris.
	 * 
	 * @return Response code message, or "" if none found.
	 */
	public static String getResponseCodeMsg_fr(String respCode) {
		return (getResponseCodeMsg(monerisCodes_fr, respCode.trim()));
	}
	
	/**
	 * Obtain local-specific-language response code message for Moneris response code.
	 * Empty language defaults to EN.
	 * 
	 * @param respCode -- response code from Moneris.
	 * @param lang -- Local language.
	 * 
	 * @see BaseAction.getDisplayLanguage().
	 * 
	 * @return Response code message, or "" if none found.
	 */
	public static String getResponseCodeMsg_local(String respCode, String lang) {
		if (lang == null || lang.startsWith("en") || lang.equals("")) {
			return (getResponseCodeMsg(monerisCodes_en, respCode.trim()));
		} else {
			return (getResponseCodeMsg(monerisCodes_fr, respCode.trim()));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.canon.payment.service.MonerisPaymentService#chechFraud(JavaAPI.Receipt
	 * )
	 */
	public String chechFraud(Receipt receipt) {
		String avsResult = receipt.getAvsResultCode();
		String cvdResult = receipt.getCvdResultCode();

		JSONObject json = new JSONObject();
		// Get avs response code
		try {
			json.put("addressFraud", (avsResult.equals("M") ? "N" : "Y"));
			json.put("addressFraudDesc", (avsResult.equals("M") ? "" : AVSCode
					.getLabel(avsResult)));
			// Get cvd response code
			if (cvdResult != null && cvdResult.length() == 2) {
				String cvdResponse = cvdResult.substring(1);
				json.put("cvsFraud", (cvdResponse.equals("M") ? "N" : "Y"));
			} else {
				json.put("cvsFraud", "Unknown");
			}
		} catch (JSONException e) {
			logger.error(e);
		}
		return json.toString();
	}

	public static void main(String[] args) {
		HttpsPostRequest mpgReq = new HttpsPostRequest("esqa.moneris.com",
				"store5", "yesguy", new Purchase("W881353-201010291420314",
						"Canon estore", "120.00", "5454545454545454", "1212",
						"7"));
		try {
			Receipt receipt = mpgReq.getReceipt();
			System.out.println("CardType = " + receipt.getCardType());
			System.out.println("TransAmount = " + receipt.getTransAmount());
			System.out.println("TxnNumber = " + receipt.getTxnNumber());
			System.out.println("ReceiptId = " + receipt.getReceiptId());
			System.out.println("TransType = " + receipt.getTransType());
			System.out.println("ReferenceNum = " + receipt.getReferenceNum());
			System.out.println("ResponseCode = " + receipt.getResponseCode());
			System.out.println("ISO = " + receipt.getISO());
			System.out.println("BankTotals = " + receipt.getBankTotals());
			System.out.println("Message = " + receipt.getMessage());
			System.out.println("AuthCode = " + receipt.getAuthCode());
			System.out.println("Complete = " + receipt.getComplete());
			System.out.println("TransDate = " + receipt.getTransDate());
			System.out.println("TransTime = " + receipt.getTransTime());
			System.out.println("Ticket = " + receipt.getTicket());
			System.out.println("TimedOut = " + receipt.getTimedOut());
		} catch (Exception e) {
			logger.error(e);
		}

	}
}

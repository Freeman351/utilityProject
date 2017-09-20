package com.freeman.payment.moneris.service;

import JavaAPI.AvsInfo;
import JavaAPI.CvdInfo;
import JavaAPI.Receipt;

import com.freeman.exception.BaseException;


public interface MonerisPaymentService {

	public abstract Receipt preAuth(String orderId, double totalAmount,
			String cardNum, String expiration, AvsInfo avs, CvdInfo cvd)
			throws BaseException;

	public abstract Receipt preAuth(String orderId, double totalAmount,
			String cardNum, String expiration, AvsInfo avs, CvdInfo cvd, String updatedCrypt)
			throws BaseException;

	public abstract Receipt cavvPreAuth(String orderId, double totalAmount, String cardNum,
			String expiration, String cavv, AvsInfo avs, CvdInfo cvd) throws BaseException;	

	public abstract Receipt capture(String orderId, double amount,
			String txnNumber) throws BaseException;

	public abstract Receipt capture(String orderId, double amount,
			String txnNumber, String updatedCrypt) throws BaseException;
	
	public abstract Receipt purchase(String orderId, double totalAmount, String cardNum,
			String expiration, AvsInfo avs, CvdInfo cvd) throws BaseException;

	public abstract Receipt purchase(String orderId, double totalAmount, String cardNum,
			String expiration, AvsInfo avs, CvdInfo cvd, String updatedCrypt) throws BaseException;
	
	public abstract Receipt cavvPurchase(String orderId, double totalAmount, String cardNum,
			String expiration, String cavv) throws BaseException;	

	public abstract Receipt voidTransaction(String orderId, String txnNumber)
			throws BaseException;

	public abstract Receipt voidTransaction(String orderId, String txnNumber, String updatedCrypt)
			throws BaseException;
	
	public abstract Receipt refund(String orderId, double amount, String txnNumber) 
			throws BaseException;

	public abstract Receipt refund(String orderId, double amount, String txnNumber, String updatedCrypt) 
			throws BaseException;
	
	public abstract boolean isApproved(String respCode);

	public abstract String chechFraud(Receipt receipt);
	
}
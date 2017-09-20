package com.freeman.payment.moneris.service;

import MpiAPI.MpiResponse;

import com.freeman.exception.BaseException;

public interface MonerisVBVPaymentService {

	public abstract MpiResponse mpiTxn(String merchantUrl, String orderId, String suffix, double totalAmount, String cardNum, 
			String expiration, String agentHeader, String acceptHeader) 
			throws BaseException;
	
	public abstract MpiResponse mpiAcs(String orderId, String suffix, String acsPaRes, String acsMD) 
			throws BaseException;

}
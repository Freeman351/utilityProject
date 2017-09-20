package com.freeman.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.freeman.model.DataBeanXALSRLSS;
import com.freeman.utilities.PdfUtil;



public class PdfPrintServlet extends HttpServlet {

	public PdfPrintServlet() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		performTask(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		performTask(request, response);
	}

	private void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

		String pdfFileName = "report2.pdf";
		String contextPath = request.getRealPath(File.separator);
		File pdfFile = new File(contextPath + pdfFileName);

		response.setContentType("application/pdf");
//		response.addHeader("Content-Disposition", "attachment; filename=" + pdfFileName);
		response.setContentLength((int) pdfFile.length());

		FileInputStream fileInputStream = new FileInputStream(pdfFile);
		OutputStream responseOutputStream = response.getOutputStream();
		int bytes;
		while ((bytes = fileInputStream.read()) != -1) {
			responseOutputStream.write(bytes);
		}
	}	

	private void performTask1(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

	    String url_itemNumber = "3755B001AA";
	    String url_itemDescription = "CST. FEEDING UNIT-AF1";
	    String url_invoiceNumber = "53613959-3755B001AA";

		PdfUtil aPdfUtil = new PdfUtil();
		response.setContentType("application/pdf");
		// download file,use this statement; display pdf file in browser, comments this statement
	    response.addHeader("Content-Disposition", "attachment; filename=" + "License_AccessNumber_Certificate");
	    
		OutputStream responseOutputStream = response.getOutputStream();
		aPdfUtil.generatePdfFile("", getServletContext().getRealPath("/") + "/LicenseAccessNumberCertificate.jasper", new HashMap<String, Object>(), 
				getDataBeanList(url_itemDescription, url_itemNumber,url_invoiceNumber), responseOutputStream);
	}	

	private List getDataBeanList(String url_itemDescription, String url_itemNumber, String url_invoiceNumber){
    	
		List aDataBeanList = new ArrayList();
		DataBeanXALSRLSS aDataBeanXALSRLSS = new DataBeanXALSRLSS(url_itemDescription, url_itemNumber,url_invoiceNumber);
		aDataBeanList.add(aDataBeanXALSRLSS);
		
		return aDataBeanList;		
	}
}

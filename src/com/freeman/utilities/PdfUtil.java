package com.freeman.utilities;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.freeman.model.DataBeanXALSRLSS;

public class PdfUtil {

	public  static void main(String[] args) {
		System.out.println("Start ....");
		// Get jasper report
		String jrxmlFileName = "C:/Users/HLi/LicenseAccessNumberCertificate.jrxml";
		String jasperFileName = "C:/Users/HLi/LicenseAccessNumberCertificate.jasper";
		String jrpirntFileName = "C:/Users/HLi/report1.jrprint";
		String pdfFileName = "C:/Users/HLi/report1.pdf";
		String pdfFileName2 = "C:/Users/HLi/report2.pdf";

		List<DataBeanXALSRLSS> aDataBeanList = new ArrayList<DataBeanXALSRLSS>();
		DataBeanXALSRLSS aDataBeanXALSRLSS = new DataBeanXALSRLSS("CST. FEEDING UNIT-AF1", "3755B001AA" ,"53613959-3755B001AA");
		aDataBeanList.add(aDataBeanXALSRLSS);

		PdfUtil aPdfUtil = new PdfUtil();
		aPdfUtil.generatePdfFile(jrxmlFileName, jasperFileName, pdfFileName2, new HashMap<String, Object>(),aDataBeanList);
//		aPdfUtil.generatePdfFile(jrxmlFileName, jasperFileName, jrpirntFileName, pdfFileName, new HashMap(), aDataList);
		System.out.println("end ....");
	}
	 
	public OutputStream generatePdfFile(String jrxmlFileName, String jasperFileName, Map<String, Object> parameters, Collection outputDataCollection, OutputStream outputStream){
		try {
//			compileJrxmlToJasper(jrxmlFileName, jasperFileName);
			JasperPrint aJasperPrint = new JasperPrint();
			try {
				aJasperPrint = JasperFillManager.fillReport(jasperFileName, parameters, getJRDataSource(outputDataCollection));
			} catch (JRException e) {
				e.printStackTrace();
			}
			// Export to OutputStream
			JasperExportManager.exportReportToPdfStream(aJasperPrint, outputStream);
		}catch (Exception e){
			e.printStackTrace();			   
		}
		return outputStream;		 
	}

	public void generatePdfFile(String jrxmlFileName, String jasperFileName, String pdfFileName, Map<String, Object> parameters, Collection outputDataCollection){
		try {
			compileJrxmlToJasper(jrxmlFileName, jasperFileName);
			JasperPrint aJasperPrint = new JasperPrint();
			try {
				aJasperPrint = JasperFillManager.fillReport(jasperFileName, parameters, getJRDataSource(outputDataCollection));
			} catch (JRException e) {
				e.printStackTrace();
			}
			// Export pdf file
			JasperExportManager.exportReportToPdfFile(aJasperPrint, pdfFileName);
		}catch (Exception e){
			e.printStackTrace();			   
		}
	}

	public void generatePdfFile(String jrxmlFileName, String jasperFileName, String jrpirntFileName, String pdfFileName, Map<String, Object> parameters, Collection outputDataCollection){
		try {
//			compileJrxmlToJasper(jrxmlFileName, jasperFileName);
			try {
				JasperFillManager.fillReportToFile(jasperFileName, parameters, getJRDataSource(outputDataCollection));
			} catch (JRException e) {
				e.printStackTrace();
			}
		   	// Export pdf file
			JasperExportManager.exportReportToPdfFile(jrpirntFileName, pdfFileName);
		}catch (Exception e){
			e.printStackTrace();			   
		}
	}

	public void compileJrxmlToJasper(String jrxmlFileName, String jasperFileName) throws Exception{
		JasperCompileManager.compileReportToFile(jrxmlFileName, jasperFileName);		
	}
	
	private JRDataSource getJRDataSource(Collection outputDataCollection){
		return new JRBeanCollectionDataSource(outputDataCollection);
	}
}

package com.freeman.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ExcelUtil {

	public  static void main(String[] args) {
		List<Object[]> excelData = getExcelData();
		try {
			(new ExcelUtil()).generateExcelFile(excelData, "C:\\JavaTemp\\new.xls", "Sample Sheet");
		    System.out.println("Excel file written successfully..");		     
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}

	public void generateExcelFile(List<Object[]> excelData, String fileName, String sheetName) 
			throws FileNotFoundException, IOException{
		excelContentOutput(getHSSFWorkbook(sheetName, excelData), getOutputStream(fileName));
	}
	
	private static List<Object[]> getExcelData(){
		List<Object[]> excelData = new ArrayList<Object[]>();
		excelData.add(new Object[] {"Emp No.", "Name", "Salary"});
		excelData.add(new Object[] {1d, "John", 1500000d});
		excelData.add(new Object[] {2d, "Sam", 800000d});
		excelData.add(new Object[] {3d, "Dean", 700000d});
		return excelData;
	}
	
	private HSSFWorkbook getHSSFWorkbook(String sheetName, List<Object[]> excelData){
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet((sheetName == null || sheetName.isEmpty() ? "Sheet1" : sheetName));
		int rowNumber = 0;
		for (Object[]  rowData : excelData){
		    Row row = sheet.createRow(rowNumber++);
		    int cellnum = 0;
			for (Object cellData : rowData){
		        Cell cell = row.createCell(cellnum++);
		        if(cellData instanceof Date){ 
		            cell.setCellValue((Date) cellData);
		        }else if(cellData instanceof Boolean){
		            cell.setCellValue((Boolean) cellData);
		        }else if(cellData instanceof String){
		            cell.setCellValue((String) cellData);
		        }else if(cellData instanceof Double){
		            cell.setCellValue((Double) cellData);		    
		        }				
			}
		}
		return workbook;
	}
	
	private FileOutputStream getOutputStream(String fileName) throws FileNotFoundException{
		return new FileOutputStream(new File(fileName));
	}
	 
	private void excelContentOutput(HSSFWorkbook workbook, FileOutputStream out) throws IOException{
	    workbook.write(out);
	    out.close();		
	}
	
}

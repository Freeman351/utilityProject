package com.freeman.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvlUtil {

	public  static void main(String[] args) {
		List<Object[]> csvDataList = getCsvData();
		try {
			(new CsvlUtil()).generateCsvFile(csvDataList, "C:\\JavaTemp\\newCsv.csv");
		    System.out.println("CSV file written successfully..");		     
		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}

	public void generateCsvFile(List<Object[]> csvData, String fileName) 
			throws FileNotFoundException, IOException{
		csvContentOutput(csvData, getOutputStream(fileName));
		
	}
	
	private static List<Object[]> getCsvData(){
		List<Object[]> csvData = new ArrayList<Object[]>();
		csvData.add(new Object[] {"Emp No.", "Name", "Salary"});
		csvData.add(new Object[] {1, "John", 1500000d});
		csvData.add(new Object[] {2, "Sam", 800000d});
		csvData.add(new Object[] {3, "Dean", 700000d});
		return csvData;
	}
	
	
	private FileOutputStream getOutputStream(String fileName) throws FileNotFoundException{
		return new FileOutputStream(new File(fileName));
	}
	 
	private void csvContentOutput(List<Object[]> csvDataList, FileOutputStream out) throws IOException{
		for (Object[] rowData : csvDataList){
			for (int i = 0; i < rowData.length; i++){
				out.write(rowData[i].toString().getBytes());
				if (i < rowData.length -1 ){
					out.write(new String(",").getBytes());					
				}
			}
			out.write(new String("\n").getBytes());
		}
	    out.close();		
	}
	
}

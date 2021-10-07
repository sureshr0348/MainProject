package com.defects.utils;

public class TempReadData {

	public static void main(String args[]) {
		ReadExcelDataFile readData = new ReadExcelDataFile(System.getProperty("user.dir") + "\\src\\main\\java\\testdata\\TestData.xlsx");
		
		int totalRows = readData.getRowCount("SampleData");
		System.out.println("total rows : " + totalRows);
		System.out.println(readData.getCellData("SampleData", 0, 3));
	}
	
	

}

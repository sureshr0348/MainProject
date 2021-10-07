package com.defects.utils;

// this is not used in framework

public class ReadTestData {
	
	public static void main(String[] args) {
		ReadExcelDataFile readData = new ReadExcelDataFile("C:\\Users\\921438\\eclipse-workspace\\testtry\\src\\main\\java\\testdata\\TestData_Testmanagement.xlsx");
		String sheetName = "Feature 1";
		String testName = "Test Three";
		
		int startRowNum = 0;
		while(!readData.getCellData(sheetName, 0, startRowNum).equalsIgnoreCase(testName)) {
			startRowNum++;
		}
		System.out.println("Test start row number :" + startRowNum);
		
		int startTestColumn = startRowNum + 1;
		int startTestRow = startRowNum + 2;
		
		int rows = 0;
		while(!readData.getCellData(sheetName, 0, startTestRow+rows).equals("")) {
			rows++;
		}
		
		System.out.println("total test rows :" + rows);
		
		int columns = 0;
		while(!readData.getCellData(sheetName, columns, startTestColumn).equals("")) {
			columns++;
		}
		
		System.out.println("total test columns :" + columns);
		
		for (int rowNumber= startTestRow; rowNumber <= startTestColumn+rows; rowNumber++) {
			for(int colNumber = 0; colNumber < columns; colNumber++) {
				System.out.println(readData.getCellData(sheetName, colNumber, rowNumber));
			}
		}
	}

}

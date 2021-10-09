package com.defects.utils;

import java.util.Hashtable;

public class TestDataProvider {
	
//	@Test(dataProvider = "getTestData")
//	public void samepleTestOne( Hashtable<String, String> table) {
//		System.out.println(table.get("Col 2"));
//	}
	
	public static Object[][] getTestData(String dataFileName,String sheetName, String testName) {
		
		ReadExcelDataFile readData = new ReadExcelDataFile(System.getProperty("user.dir") + "\\src\\test\\resources\\objectRepository\\" + dataFileName + ".xlsx");
		
		int startRowNum = 0;
		while(!readData.getCellData(sheetName, 0, startRowNum).equalsIgnoreCase(testName)) {
			startRowNum++;
		}
		//System.out.println("Test start row number :" + startRowNum);
		
		int startTestColumn = startRowNum + 1;
		int startTestRow = startRowNum + 2;
		
		int rows = 0;
		while(!readData.getCellData(sheetName, 0, startTestRow+rows).equals("")) {
			rows++;
		}
		
		//System.out.println("total test rows :" + rows);
		
		int columns = 0;
		while(!readData.getCellData(sheetName, columns, startTestColumn).equals("")) {
			columns++;
		}
		
		//System.out.println("total test columns :" + columns);
		
		Object[][] dataSet = new Object[rows][1];
		
		Hashtable<String, String> dataTable = null;
		int dataRowNumber = 0;
		
		for (int rowNumber= startTestRow; rowNumber <= startTestColumn+rows; rowNumber++) {
			dataTable = new Hashtable<String, String>();
			for(int colNumber = 0; colNumber < columns; colNumber++) {
				String key = readData.getCellData(sheetName, colNumber, startTestColumn);
				String value = readData.getCellData(sheetName, colNumber, rowNumber);
				dataTable.put(key, value);
				//System.out.println(readData.getCellData(sheetName, colNumber, rowNumber));
			}
			dataSet[dataRowNumber][0] = dataTable;
			dataRowNumber++;
		}
		
		return dataSet;
	}

}

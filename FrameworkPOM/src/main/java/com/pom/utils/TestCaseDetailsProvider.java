package com.pom.utils;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestCaseDetailsProvider {
	public static String[][] getAllTestDetails(String sheetName) throws IOException {
		String[][] data = null;
		int i,j;
		String cellValue = "";
		
		try {
				FileInputStream fis = new FileInputStream("./data/"+sheetName+".xlsx");
				XSSFWorkbook wBook = new XSSFWorkbook(fis);
				XSSFSheet wSheet = wBook.getSheet("TestDetails");
				int rowCount = wSheet.getLastRowNum() + 1;
				//System.out.println("Last row number : "+wSheet.getLastRowNum()+" ,First cell numb: "+wSheet.getRow(0).getFirstCellNum()+" Last column numb = "+wSheet.getRow(0).getLastCellNum());
				int colCount = wSheet.getRow(0).getLastCellNum();
				data = new String[rowCount][colCount];
				for (i = 0; i < rowCount; i++) {
					try {
						XSSFRow row = wSheet.getRow(i);
						for (j = 0; j < colCount; j++) {
							try {
							//if (row.getCell(j).getCellType() == 1) {
								cellValue = row.getCell(j).getStringCellValue();
							//} else if (row.getCell(j).getCellType() == 0) {
								//cellValue = "" + row.getCell(j).getNumericCellValue();
							//}
							}
							catch(Exception e) {
								
							}

							data[i][j] = cellValue;
						}
					} catch (NullPointerException e) {
					//	System.out.println("Error 2");
						e.printStackTrace();

					}
				}
				//System.out.println("Data = " + data[1][1]);
				wBook.close();

			} catch (NullPointerException e) {
				//System.out.println("Error 3");
				e.printStackTrace();

			}
		/*for( String[] datas : data ) {
		//	System.out.println("Datas in return : " + datas);
		}*/
		return data;
	}
	
	}
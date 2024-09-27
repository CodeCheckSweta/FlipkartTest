package com.flipkart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public final class ExcelUtils {
	private ExcelUtils() {}
	private static final String filePath= "resources/excel/testData.xlsx";
	
	public static List<Map<String,String>> readExcelData(String sheetName){
		List<Map<String, String>> list = new ArrayList<>();
    	FileInputStream fis = null;
        Workbook workbook = null;
        
		try{
			fis = new FileInputStream(filePath);
			workbook = new XSSFWorkbook(fis);
			Sheet sheet = workbook.getSheet(sheetName);
			
         	if (sheet == null) {
                 throw new RuntimeException("Sheet with name '" + sheetName + "' not found in the Excel file.");
            }
	
			int lastrownum = sheet.getPhysicalNumberOfRows();
			int lastcolnum = sheet.getRow(0).getPhysicalNumberOfCells();
			
			for(int i=1;i<lastrownum;i++) {				// Starting from row 1 for data, assuming row 0 has headers
				Map<String, String> map = new HashMap<>();
				for(int j=0;j<lastcolnum;j++) {
					String key = sheet.getRow(0).getCell(j).getStringCellValue();
					String value = sheet.getRow(i).getCell(j).getStringCellValue();
					map.put(key, value);
				}
				list.add(map);
			}		
			System.out.println(list);
			return list;
		}catch(FileNotFoundException e) {
			throw new InvalidPathException("Excel File you trying to read is not found", filePath);
		}catch (Exception e) {
			System.err.println("Error reading Excel file at '" + filePath + "': " + e.getMessage());
		}finally {
        	// Close resources
            try {
                if (workbook != null) {
                    workbook.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                System.err.println("Error closing Excel file resources: " + e.getMessage());
            }
        }
		return list;
	}
}

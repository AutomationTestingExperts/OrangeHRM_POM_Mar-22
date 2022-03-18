package com.orangehrm.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	
	public String readValueFromExcel(String path, int rowNum, int colNum) throws IOException {
		File file  = new File(path);
		FileInputStream fi = new FileInputStream(file);
		Workbook book = new XSSFWorkbook(fi); //--> .xlsx 
//		Workbook book2 = new HSSFWorkbook(); //.xls
		Sheet sheet = book.getSheet("Sheet1");
		Row row = sheet.getRow(rowNum);
		int colCount = row.getLastCellNum();
		System.out.println("Total columns available : "+colCount);
		int rowCount = sheet.getLastRowNum();
		System.out.println("Total rows available : "+rowCount);
		Cell cell = row.getCell(colNum);
		String val = cell.getStringCellValue();
//		System.out.println(val);
		return val;
	}
	
	public List<String> getColValues(String path,int col) throws IOException {
		List<String> list = new ArrayList<String>();
		File file  = new File(path);
		FileInputStream fi = new FileInputStream(file);
		Workbook book = new XSSFWorkbook(fi); //--> .xlsx 
//		Workbook book2 = new HSSFWorkbook(); //.xls
		Sheet sheet = book.getSheet("Sheet1");
		Row row1 = sheet.getRow(0);
		int colCount = row1.getLastCellNum();
		
		
//		System.out.println("Total columns available : "+colCount);
		int rowCount = sheet.getLastRowNum();
		for(int i=0;i<rowCount;i++) {
			Row row = sheet.getRow(i);
			Cell cell = row.getCell(col);
			list.add(cell.getStringCellValue());
		}
//		System.out.println("Total rows available : "+rowCount);
		
//		System.out.println(val);
		return list;
		
	}
	

}

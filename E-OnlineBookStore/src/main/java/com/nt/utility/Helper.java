package com.nt.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.nt.entity.BookExcelFile;

public class Helper {

	// to check the file context type
	public static boolean checkExcelFile(MultipartFile file) {
		System.out.println("check file");
		return file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	}
	
	
	public static List<BookExcelFile>  excelFileInsertDatabase(InputStream io) throws IOException{
		
		// create empty list to store final book data
		List<BookExcelFile> list = new ArrayList<>();
		
		//use set collection to store the unique book record
		Set<BookExcelFile> setbook = new HashSet<>();
		
		//create workbook object from inputstream to load the excel file
		XSSFWorkbook work = new XSSFWorkbook(io);

		//get first sheet from the work book
		
		XSSFSheet sheet = work.getSheetAt(0);
		
		Iterator<Row> iteratorRow = sheet.iterator();
		
		// here we are skipping the column name row
		if(iteratorRow.hasNext()) {
			iteratorRow.next();
		}
		
		// after column name remaining data we are fetching
		
		while(iteratorRow.hasNext()) {
			
			// we are getting current row here
			Row row = iteratorRow.next();
			
			// getting each cell from the current row
			Iterator<Cell> cell = row.iterator();
			
			// creating the BookExcelFile object to store the data from excel file
			BookExcelFile book = new BookExcelFile();
			
			// getting each cell index and its data
			while(cell.hasNext()) {
				
				Cell cellnext = cell.next();
				
				switch (cellnext.getColumnIndex()) {
				case  0: 
					book.setProductName(cellnext.getStringCellValue());
					break;
				case 1:
					book.setDescription(cellnext.getStringCellValue());
					break;
				case 2:
					book.setPrice(cellnext.getNumericCellValue());
					break;
				
				default:
					break;
					
					
				}// end switch
				// check the file is unique or not and then store into the list
				if(setbook.add(book)){
					list.add(book);	
				}
			
			}// inner while loop
			
		}// outer while loop
		// return the book of list having unique data
		return list;
	}
}

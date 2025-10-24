package com.nt.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nt.entity.BookExcelFile;
import com.nt.model.ResponseMessage;
import com.nt.service.IBookExcelFileService;
import com.nt.utility.Constants;
import com.nt.utility.Helper;


@RestController
public class BookExcelController {
	
	
	@Autowired
	private IBookExcelFileService bookFileService;
	
	
	@PostMapping("/saveexcelfile")
	public ResponseEntity<ResponseMessage> saveExcelFileData(@RequestParam MultipartFile file) throws IOException {
		System.out.println("Uploaded file content type: " + file.getContentType());

		boolean checkExcelFile = Helper.checkExcelFile(file);
		System.out.println(checkExcelFile);
		if(Helper.checkExcelFile(file)) {
			bookFileService.SaveExcelFile(file);
			System.out.println("saved file");
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS, "Excel File Record store successfully...!"));
		}
		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "Excel File not store successfully...!"));
	}
	
	@GetMapping("/fetchexcelfile")
	public ResponseEntity<ResponseMessage> getExcelFileData() {
		
			List<BookExcelFile> files =bookFileService.findExcelFileData();
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS, "Excel File Record Fetched successfully...!",files));
	}
	
 
}

package com.nt.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;


import com.nt.service.IFileMngtService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;



@RestController
@Tag(name="FileController" ,description = "storing files into database")
public class FileController {
	
	@Autowired
	private IFileMngtService fileService;

	@PostMapping(value="/uploadfile")
	@Operation(
		    summary = "Add new File",
		    description = "Insert a new file into the database",
		    responses = {
		        @ApiResponse(responseCode = "201", description = "file created successfully"),
		        @ApiResponse(responseCode = "400", description = "Invalid file data"),
		        @ApiResponse(responseCode = "500", description = "Internal server error")
		    }
		)
	public ResponseEntity<String> uploadFile(@RequestParam MultipartFile file) throws IOException {
		
		String response = fileService.storeFile(file);
		
		return ResponseEntity.ok(response);
	}
	
	@PostMapping(value="/uploadmutliFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<List<Object>> uploadFiles(@RequestParam MultipartFile[] file) throws IOException{
		
	List<Object> list = Arrays.stream(file).map(s -> {
		
		try {
			return uploadFile(s);//.getBody();
			
		}catch(Exception e) {
			return "File Failed to upload"+ e.getLocalizedMessage(); // our language messages
		}
//		return null;
	}).collect(Collectors.toList());
	return ResponseEntity.ok(list);
		
		
	}
	
	
}

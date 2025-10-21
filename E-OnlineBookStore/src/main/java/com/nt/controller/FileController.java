package com.nt.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nt.service.IFileMngtService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;



@RestController
@Tag(name="FileController" ,description = "storing files into database")
public class FileController {
	
	@Autowired
	private IFileMngtService fileService;
	
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(FileController.class);

	@PostMapping(value="/uploadfile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@Operation(
		    summary = "Add new File",
		    description = "Insert a new file into the database",
		    responses = {
		        @ApiResponse(responseCode = "201", description = "file created successfully"),
		        @ApiResponse(responseCode = "400", description = "Invalid file data"),
		        @ApiResponse(responseCode = "500", description = "Internal server error")
		    }
		)
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
		logger.info("FileController uploadFile method execution is started..!");
		String response = fileService.storeFile(file);
		logger.info("FileController uploadFile method execution is ended..!");
		return ResponseEntity.ok(response);
	}
	
	@PostMapping(value="/uploadmutliFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<List<Object>> uploadFiles(@RequestParam("file") MultipartFile[] file) throws IOException{
		logger.info("FileController uploadFiles method execution is started..!");
	List<Object> list = Arrays.stream(file).map(s -> {
		
		try {
			logger.info("file is uploading using uploadFile() method of its own class..!");
			return uploadFile(s);//.getBody();
			
		}catch(Exception e) {
			logger.error("Error occur during fie upload  "+ e.getMessage());
			return "File Failed to upload"+ e.getLocalizedMessage(); // our language messages
		}
//		return null;
	}).collect(Collectors.toList());
	logger.info("FileController uploadFiles method execution is ended..!");
	return ResponseEntity.ok(list);
		
		
	}
	
	
}

package com.nt.controller;

import java.net.HttpURLConnection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nt.entity.UserRegister;
import com.nt.model.ResponseMessage;
import com.nt.model.UserRequest;
import com.nt.model.UserRequest3Dto;
import com.nt.model.UserRequestDto;
import com.nt.service.IUserRegisterService;
import com.nt.utility.Constants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@Tag(name = "UserRegisterController", description = "insert user Register Data to data base")
public class UserRegisterController {

	@Autowired
	private IUserRegisterService userRegisterService;
	


  
	
	@PostMapping("/userregister")
	@Operation(
	    summary = "Add new user",
	    description = "Insert a new user record into the database",
	    responses = {
	        @ApiResponse(responseCode = "201", description = "User record created successfully"),
	        @ApiResponse(responseCode = "400", description = "Invalid request data"),
	        @ApiResponse(responseCode = "409", description = "User with email already exists"),
	        @ApiResponse(responseCode = "500", description = "Internal server error")
	    }
	)
	public ResponseEntity<ResponseMessage> createUserRegister(@RequestBody UserRequestDto userRequestDto) {
		
		try {
			if(userRequestDto.getEmail()==null || userRequestDto.getEmail().isEmpty() || userRequestDto.getPassword()==null|| userRequestDto.getPassword().isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, " email and passwprd should not be empty"));
			}
		UserRegister userRegister = userRegisterService.insertUserRegister(userRequestDto);
		 if(userRegister != null) {
			 return  ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS, "User Registered Successfully", userRegister));
//	return  ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS, "User Register Successfully!!!!", userRequestDto));
		 }else{
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, " input mismatch"));
		
		 }
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED,"Internal server error"));
		}
	}
	
	@GetMapping("/userDetails/{id}")
	@Operation(
	        summary = "get user details",
	        description = "Get user First Name and last name",
	        responses = {
	            @ApiResponse(
	                responseCode = "200",
	                description = "User data fetch successfully",
	                content = @Content(
	                    mediaType = "application/json"
	                )
	            )
	        }
	    )
	public ResponseEntity<ResponseMessage> getLoginDetails(@PathVariable Integer id){
		
		UserRequest userDetails = userRegisterService.getUserDetails(id);
		return  ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(HttpURLConnection.HTTP_OK,Constants.SUCCESS, "Successfully Fetched", userDetails));
		
	}
	
	@PostMapping("/userLogin")
	@Operation(
	    summary = "Use Login",
	    description = "Validate User Details for Login",
	    responses = {
	        @ApiResponse(responseCode = "200", description = "User login successfully"),
	        @ApiResponse(responseCode = "400", description = "Invalid request data"),
	        @ApiResponse(responseCode = "500", description = "Internal server error")
	    }
	)
	public ResponseEntity<ResponseMessage> checkLogin(@RequestBody UserRequestDto userRequestDto) {
		
		try {
			
			if(userRequestDto.getEmail()==null || userRequestDto.getEmail().isEmpty() || userRequestDto.getPassword()==null|| userRequestDto.getPassword().isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, " email and passwprd should not be empty"));
			}
			
		UserRegister userRegister = userRegisterService.checkUserDetails(userRequestDto);
		System.out.println("if out  -- userRegister " +userRegister);
		 if(userRegister != null) {
				System.out.println("if in -- userRegister " +userRegister);
			 return  ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(HttpURLConnection.HTTP_OK,Constants.SUCCESS, "User Login Successfully...!", userRegister));
			// return  ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS, "User Login Successfully!!!!", userRequestDto));
		 }else{
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, " invalid Credential....!"));
		
		 }
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED,"Internal server error"));
		}
	}
	
	@GetMapping("/userDetails3/{id}")
	@Operation(
	        summary = "get  3 user details",
	        description = "Get user First Name, last name and email",
	        responses = {
	            @ApiResponse(
	                responseCode = "200",
	                description = "User data fetch successfully",
	                content = @Content(
	                    mediaType = "application/json"
	                )
	            )
	        }
	    )
	public UserRequest3Dto getLoginDetails3(@PathVariable Integer id){
		
		UserRequest3Dto userDetails = userRegisterService.getUserDetails3(id);
		return  userDetails;
		
	}
	

	@PostMapping("/userregisteruploadmultifile")
	public ResponseEntity<ResponseMessage> postMethodName(@RequestParam String jsonData , @RequestParam MultipartFile[] files) {
	
		try {
			
			UserRequestDto userObject = new ObjectMapper().readValue(jsonData, UserRequestDto.class);
		
		UserRegister uploadMulti = userRegisterService.uploadMultiUserRegister(userObject , files);
		 if(uploadMulti != null) {
			 return  ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS, "User Registered Successfully", uploadMulti));
//	return  ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS, "User Register Successfully!!!!", userRequestDto));
		 }else{
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, " input mismatch"));
		
		 }
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED,"Internal server error"));
		}
		
	}
	
	
	@GetMapping("/getallusersdetails")
	
	public List<UserRegister> getAllUsersDetailsData() {
		return userRegisterService.getAllUserRegisterData();
	}
	

}
	


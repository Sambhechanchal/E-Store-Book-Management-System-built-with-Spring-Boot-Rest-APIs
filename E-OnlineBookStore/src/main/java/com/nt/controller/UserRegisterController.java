package com.nt.controller;

import java.net.HttpURLConnection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	//private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserRegisterController.class);
	private static final Logger logger = LogManager.getLogger(CustomerController.class);
	
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
		logger.info("UserRegisterController createUserRegister method execution is start.....!");
		try {
			logger.debug("UserRegisterController userRequestDto input received.. !");
			logger.warn("email and password validation");
			
			if(userRequestDto.getEmail()==null || userRequestDto.getEmail().isEmpty() || userRequestDto.getPassword()==null|| userRequestDto.getPassword().isEmpty()) {
				logger.error("email or the passowrd is empty error occur");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, " email and passwprd should not be empty"));
			}
		UserRegister userRegister = userRegisterService.insertUserRegister(userRequestDto);
		 if(userRegister != null) {
			 logger.info("user register save and return the userRegister object successfully.!\"E-OnlineBookStore\"");
			 return  ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS, "User Registered Successfully", userRegister));
//	return  ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS, "User Register Successfully!!!!", userRequestDto));
		 }else{
			 logger.info("userRegistero object is empty-- \"E-OnlineBookStore\"");
			 logger.info("method execution is ended here");
			 logger.warn("failure error occur due to userRegister object is empty...!");
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, " input mismatch"));
		
		 }
		}catch(Exception e) {
			logger.error("In createUserRegister of UserRegisterController internal server problem arise "+ e.getMessage());
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
		logger.info("UserRegisterController getLoginDetails method execution stated..!");
		UserRequest userDetails = userRegisterService.getUserDetails(id);
		logger.info("UserRegisterController getLoginDetails method execution ended");
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
		logger.info("UserRegisterController checkLogin method execution is started...!");
		try {
			logger.debug("UserRegisterController in checkLogin method userRequestDto input received.. !");
			logger.warn("email and password validation");
			
			if(userRequestDto.getEmail()==null || userRequestDto.getEmail().isEmpty() || userRequestDto.getPassword()==null|| userRequestDto.getPassword().isEmpty()) {
				logger.error("email or the password should not be empty..!");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, " email and passwprd should not be empty"));
			}
			
		UserRegister userRegister = userRegisterService.checkUserDetails(userRequestDto);
		
//		System.out.println("if out  -- userRegister " +userRegister);
		 if(userRegister != null) {
			 logger.info("user register save and return the userRegister object successfully.!\"E-OnlineBookStore\"");
				//System.out.println("if in -- userRegister " +userRegister);
			 return  ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(HttpURLConnection.HTTP_OK,Constants.SUCCESS, "User Login Successfully...!", userRegister));
			// return  ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS, "User Login Successfully!!!!", userRequestDto));
		 }else{
			 logger.info("userRegistero object is empty-- \"E-OnlineBookStore\"");
			 logger.info("method execution is ended here");
			 logger.warn("failure error occur due to userRegister object is empty...!");
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, " invalid Credential....!"));
		
		 }
		}catch(Exception e) {
			logger.error("In checkLogin of UserRegisterController internal server problem arise "+ e.getMessage());
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
	public UserRegister getLoginDetails3(@PathVariable Integer id){
		
		UserRegister userDetails = userRegisterService.getUserDetails3(id);
		return  userDetails;
		
	}
	

	@PostMapping("/userregisteruploadmultifile")
	public ResponseEntity<ResponseMessage> postMethodName(@RequestParam String jsonData , @RequestParam MultipartFile[] files) {
	 logger.info("UserRegisterController postMethodName methods execution started..!");
		try {
			logger.info("Converting JSON data input to java object..");
			UserRequestDto userObject = new ObjectMapper().readValue(jsonData, UserRequestDto.class);
		 logger.info("JSON to java object converted successfully..!");
		UserRegister uploadMulti = userRegisterService.uploadMultiUserRegister(userObject , files);
		
		 if(uploadMulti != null) {
			 logger.info("multiple file uploaded and return userRegister object");
			 logger.info("UserRegisterController postMethodName methods execution ended..!");
			 return  ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS, "User Registered Successfully", uploadMulti));
//	return  ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS, "User Register Successfully!!!!", userRequestDto));
		 }else{
			 logger.info("return empty userRegister object");
			 logger.info("UserRegisterController postMethodName methods execution ended..!");
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, " input mismatch"));
		
		 }
		}catch(Exception e) {
			logger.error("In postMethodName of UserRegisterController internal server problem arise "+ e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED,"Internal server error"));
		}
		
	}
	
	
	@GetMapping("/getallusersdetails")
	
	public List<UserRegister> getAllUsersDetailsData() {
		logger.info("UserRegisterController getAllUsersDetailsData methods execution started..!");
		logger.info("UserRegisterController getAllUsersDetailsData methods execution ended..!");
		return userRegisterService.getAllUserRegisterData();
	}
	

}
	


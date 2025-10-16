package com.nt.controller;

import java.net.HttpURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nt.entity.CustomerEntity;
import com.nt.model.ResponseMessage;
import com.nt.service.ICustomerService;
import com.nt.utility.Constants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;




@RestController
//@ControllerAdvice
public class CustomerController {

	@Autowired
	private ICustomerService custService;
	
	@PostMapping("/savecustomer")
	@Operation(
		    summary = "Add new Customer",
		    description = "Insert a new Customer record into the database",
		    responses = {
		        @ApiResponse(responseCode = "201", description = "Customer record created successfully"),
		        @ApiResponse(responseCode = "400", description = "Invalid request data"),
		        @ApiResponse(responseCode = "500", description = "Internal server error")
		    }
		)
	public ResponseEntity<ResponseMessage> saveCustomer(@RequestBody CustomerEntity cust) {
		
		try {
		
		if(cust.getEmail()== null || cust.getEmail().isEmpty()|| cust.getName()== null|| cust.getName().isEmpty() ) {
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILUER , "Email and name should not be empty....!"));
		}
		 CustomerEntity saveCustomerRecord = custService.saveCustomerRecord(cust);
		  if(saveCustomerRecord !=null) {
			  return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS , "Customer Record saved successfuly ....!", saveCustomerRecord)); 
		  }else {
			  return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILUER , "Customer Record Failed To save....!"));
		  }
	
		}catch(Exception e) {
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED , "Internal Server Error....!"));
		}
	}
	
	@PutMapping("/customerupdate")
	@Operation(
		    summary = "update Existing customer record",
		    description = "update Existing customer record",
		    responses = {
		        @ApiResponse(responseCode = "200", description = "Customer record updated successfully"),
		        @ApiResponse(responseCode = "400", description = "Invalid request data"),
		        @ApiResponse(responseCode = "500", description = "Internal server error")
		    }
		)
	public ResponseEntity<ResponseMessage>  updateCustomer(@RequestBody CustomerEntity cust) {
//		try {
			if(cust.getEmail()== null || cust.getEmail().isEmpty()|| cust.getName()== null|| cust.getName().isEmpty() ) {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILUER , "Email and name should not be empty....!"));
			}
			
			  if(cust.getId()==null) {
				  CustomerEntity updateCustomerRecord = custService.saveCustomerRecord(cust);
				  return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS , "Customer Record saved successfuly ....!", updateCustomerRecord)); 
			  }else {
				  CustomerEntity updateCustomerRecord = custService.updateCustomerRecord(cust);
				  return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS, "Customer Record updated successfully....!", updateCustomerRecord));
			  }
			
				/*}catch(Exception e) {
					return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED , "Internal Server Error....!"));
				}*/
	}
	
	@PostMapping("/createOrupdateCustomerRecord")
	@Operation(
		    summary = "update or save  customer record",
		    description = "update or save customer record",
		    responses = {
		        @ApiResponse(responseCode = "201", description = "Customer record saved successfully"),
		        @ApiResponse(responseCode = "200", description = "Customer record updated successfully"),
		        @ApiResponse(responseCode = "400", description = "Invalid request data"),
		        @ApiResponse(responseCode = "500", description = "Internal server error")
		    }
		)
	public ResponseEntity<ResponseMessage> updateCustRecord(@RequestBody CustomerEntity cust ) {
		
		try {
			if(cust.getEmail()== null || cust.getEmail().isEmpty()|| cust.getName()== null|| cust.getName().isEmpty() ) {
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILUER , "Email and name should not be empty....!"));
			}
			CustomerEntity updateCustomerRecord = custService.updateCustomerRecord(cust);
			  if(cust.getId()==null) {
				  return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS , "Customer Record saved successfuly ....!", updateCustomerRecord)); 
			  }else {
				  return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS, "Customer Record updated successfully....!", updateCustomerRecord));
			  }
			
		}catch(Exception e) {
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED , "Internal Server Error....!"));
		}
	}
	
	@GetMapping("/getCustomerRecord/{id}")
	@Operation(
		    summary = "get single customer details",
		    description = "get customer record",
		    responses = {
		        @ApiResponse(responseCode = "200", description = "Customer record fetched successfully"),
		        @ApiResponse(responseCode = "400", description = "Invalid request data"),
		        @ApiResponse(responseCode = "500", description = "Internal server error")
		    }
		)
	public ResponseEntity<ResponseMessage>  getCustomerRecord(@PathVariable Long id) {
		CustomerEntity customerEntity = custService.getCustomerEntity(id);
		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS, "Customer Record Fetch Successfully...!",customerEntity ));
	}
	
	@GetMapping("/getAllCustomerRecord")
	@Operation(
		    summary = "get All customer details",
		    description = "get all customer record",
		    responses = {
		        @ApiResponse(responseCode = "200", description = "All Customer record fetched successfully"),
		        @ApiResponse(responseCode = "400", description = "Invalid request data"),
		        @ApiResponse(responseCode = "500", description = "Internal server error")
		    }
		)
	public Iterable<CustomerEntity> getAllCustomer() {
		return custService.getAllCustomerEntity();
	}
	
	@DeleteMapping("/deletecustomer/{id}")
	@Operation(
		    summary = "delete single customer ",
		    description = "delete customer record",
		    responses = {
		        @ApiResponse(responseCode = "200", description = "Customer record deleted successfully"),
		        @ApiResponse(responseCode = "400", description = "Invalid request data"),
		        @ApiResponse(responseCode = "500", description = "Internal server error")
		    }
		)
	public String deleteCustomerById(@PathVariable Long id) {
		
		CustomerEntity deleteCustomer = custService.deleteCustomer(id);
		return  deleteCustomer +" customer record deleted....!";
	}

	@DeleteMapping("/deleteallcustomer")
	@Operation(
		    summary = "delete All customer records ",
		    description = "delete all customer record",
		    responses = {
		        @ApiResponse(responseCode = "200", description = "All Customer record deleted successfully"),
		        @ApiResponse(responseCode = "400", description = "Invalid request data"),
		        @ApiResponse(responseCode = "500", description = "Internal server error")
		    }
		)
	public String deleteAllCustomer() {
		
		String msg = custService.deleteAllCustomer();
		return " All customer record deleted successfully......!";
	}
	
	@GetMapping("/getallcustomerwithpagination")
	public ResponseEntity<ResponseMessage> getAllCustomerWithPage(@RequestParam int pagenumber, @RequestParam int pagesize, 
			@RequestParam String field , @RequestParam String sortOrder ){
		
		if(pagenumber == -1 || pagesize == 0 || field == null || field.isEmpty() || sortOrder== null || sortOrder.isEmpty()) {
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILUER, "passing data should not be empty...!"));
		}
		Page<CustomerEntity> customerByPage = custService.getCustomerByPage(pagenumber, pagesize, field, sortOrder);
		 
		 if(customerByPage != null) {
			 return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK,Constants.SUCCESS, "customer record fetch successfully ...!", customerByPage));
		 }else {
			 return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "Customer Record faild to Fetch...!"));
		 }
		
	}
	
}

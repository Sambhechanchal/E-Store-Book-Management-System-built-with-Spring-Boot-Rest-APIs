package com.nt.controller;

import java.net.HttpURLConnection;

import org.slf4j.LoggerFactory;
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
	
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	// saveCustomer(cust) method
	
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
		logger.info("CustomerController saveCustomer method execution is started..!");
		try {
		logger.debug("CustomerEntity object is recieved"+ cust);
		logger.warn("customer email and name validation will happens...");
		if(cust.getEmail()== null || cust.getEmail().isEmpty()|| cust.getName()== null|| cust.getName().isEmpty() ) {
			logger.error(" customerEntity email or name should not be empty....");
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILUER , "Email and name should not be empty....!"));
		}
		 CustomerEntity saveCustomerRecord = custService.saveCustomerRecord(cust);
		  if(saveCustomerRecord !=null) {
			  logger.info("CustomerEntity object is saved by saveCustomerRecord() method ");
			  logger.info("CustomerController saveCustomer method execution is ended.!");
			  return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS , "Customer Record saved successfuly ....!", saveCustomerRecord)); 
		  }else {
			  logger.info("CustomerEntity object is empty");
			  logger.info("CustomerController saveCustomer method execution is ended.!");
			  logger.warn("failure error occur due to CustomerEntity object is empty...!");
			  return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILUER , "Customer Record Failed To save....!"));
		  }
	
		}catch(Exception e) {
			logger.error("In saveCustomer of CustomerController internal server problem arise "+ e.getMessage());
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED , "Internal Server Error....!"));
		}
	}
	
	 // updateCustomer(cust) method
	
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
		logger.info("CustomerController updateCustomer method execution is started..!");
		
		logger.debug("CustomerEntity object is recieved"+ cust);
		logger.warn("customer email and name validation will happens...");
			if(cust.getEmail()== null || cust.getEmail().isEmpty()|| cust.getName()== null|| cust.getName().isEmpty() ) {
				logger.error(" customerEntity email or name should not be empty....");
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILUER , "Email and name should not be empty....!"));
			}
			
			  if(cust.getId()==null) {
				  logger.info("customer id is null saved the record.!");
				  CustomerEntity updateCustomerRecord = custService.saveCustomerRecord(cust);
				  logger.info("Customer recored saved successfully..!!");
				  logger.info("CustomerController updateCustomer method execution is ended..!");
				  return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS , "Customer Record saved successfuly ....!", updateCustomerRecord)); 
			  }else {
				  logger.info("Customer id is not null update the existinf record");
				  CustomerEntity updateCustomerRecord = custService.updateCustomerRecord(cust);
				  logger.info("Customer record saved successfully..!");
				  logger.info("CustomerController updateCustomer method execution is ended..!");
				  return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS, "Customer Record updated successfully....!", updateCustomerRecord));
			  }
			
				/*}catch(Exception e) {
					return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED , "Internal Server Error....!"));
				}*/
	}
	
	// updateCustRecord(cust) method
	
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
		logger.info("CustomerController updateCustRecord method execution is started..!");
		try {
			logger.debug("CustomerEntity object is recieved"+ cust);
			logger.warn("customer email and name validation will happens...");
			if(cust.getEmail()== null || cust.getEmail().isEmpty()|| cust.getName()== null|| cust.getName().isEmpty() ) {
				logger.error(" customerEntity email or name should not be empty....");
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILUER , "Email and name should not be empty....!"));
			}
			CustomerEntity updateCustomerRecord = custService.updateCustomerRecord(cust);
			  if(cust.getId()==null) {
				  logger.info("customer object is saved and method execution ended...!");
				  return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS , "Customer Record saved successfuly ....!", updateCustomerRecord)); 
			  }else {
				  logger.info("customer onject is updated..!");
				  logger.info("CustomerController updateCustRecord method execution is ended..!");
				  return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS, "Customer Record updated successfully....!", updateCustomerRecord));
			  }
			
		}catch(Exception e) {
			logger.error("In updateCustRecord of CustomerController internal server problem arise "+ e.getMessage());
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED , "Internal Server Error....!"));
		}
	}
	
	// getCustomerRecords(id) method
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
		logger.info("CustomerController updateCustomer method execution is started..!");
		CustomerEntity customerEntity = custService.getCustomerEntity(id);
		logger.info("CustomerController updateCustomer method execution is started..!");
		return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS, "Customer Record Fetch Successfully...!",customerEntity ));
	}
	
	// getAllCustomer methods
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
		logger.info("CustomerController deleteCustomerById method execution is started..!");
		CustomerEntity deleteCustomer = custService.deleteCustomer(id);
		logger.info("CustomerController deleteCustomerById method execution is started..!");
		return  deleteCustomer +" customer record deleted....!";
	}

	// delete method
	
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
		logger.info("CustomerController deleteAllCustomer method execution is started..!");
		String msg = custService.deleteAllCustomer();
		logger.info("CustomerController deleteAllCustomer method execution is started..!");
		return " All customer record deleted successfully......!";
	}
	
	
	// pagination methods
	
	@GetMapping("/getallcustomerwithpagination")
	public ResponseEntity<ResponseMessage> getAllCustomerWithPage(@RequestParam int pagenumber, @RequestParam int pagesize, 
			@RequestParam String field , @RequestParam String sortOrder ){
		logger.info("CustomerController getAllCustomerWithPage method execution is started..!");
		
		logger.debug("input RequestParam are recieved");
		logger.warn("customer email and name validation will happens...");
		if(pagenumber == -1 || pagesize == 0 || field == null || field.isEmpty() || sortOrder== null || sortOrder.isEmpty()) {
			logger.error(" customerEntity email or name should not be empty....");
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILUER, "passing data should not be empty...!"));
		}
		Page<CustomerEntity> customerByPage = custService.getCustomerByPage(pagenumber, pagesize, field, sortOrder);
		 
		 if(customerByPage != null) {
			 logger.info("CustomerController getAllCustomerWithPage method execution ended..!");
			 return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK,Constants.SUCCESS, "customer record fetch successfully ...!", customerByPage));
		 }else {
			 logger.info("CustomerController getAllCustomerWithPage method execution is ended..!");
			 logger.error("Error occure due to Page<CustomerEntity> object is empty");
			 return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILED, "Customer Record faild to Fetch...!"));
		 }
		
	}
	
}

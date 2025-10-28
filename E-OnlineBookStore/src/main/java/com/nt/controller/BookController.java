package com.nt.controller;

import java.net.HttpURLConnection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nt.entity.BookModule;
import com.nt.entity.CustomerEntity;
import com.nt.model.ResponseMessage;
import com.nt.service.IBookService;
import com.nt.utility.Constants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


@RestController
public class BookController {

	 Logger logger = LogManager.getLogger(BookController.class);
	@Autowired
	private IBookService bookService;
	
	// updat and save Book Record method
	
		@PostMapping("/createOrupdateBookRecord")
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
		public ResponseEntity<ResponseMessage> saveOrUpdateBookRecord(@RequestBody BookModule bookDto ) {
			logger.info("BookController saveOrUpdateBookRecord method execution is started..!");
			try {
				logger.debug("CustomerEntity object is recieved"+ bookDto);
				logger.warn("customer email and name validation will happens...");
				if(bookDto.getBookName()== null || bookDto.getBookName().isEmpty()|| bookDto.getAuthor()== null|| bookDto.getAuthor().isEmpty() ) {
					logger.error(" customerEntity email or name should not be empty....");
					return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_BAD_REQUEST, Constants.FAILUER , "Email and name should not be empty....!"));
				}
				BookModule bookRecord = bookService.saveUpdateBookRecord(bookDto);
				  if(bookDto.getId()==null) {
					  logger.info("book data is saved and method execution ended...!");
					  return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS , "Customer Record saved successfuly ....!", bookRecord)); 
				  }else {
					  logger.info("book data is updated..!");
					  logger.info("BookController saveOrUpdateBookRecord method execution is ended..!");
					  return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_CREATED, Constants.SUCCESS, "Customer Record updated successfully....!", bookRecord));
				  }
				
			}catch(Exception e) {
				logger.error("In saveOrUpdateBookRecord of BookController internal server problem arise "+ e.getMessage());
				return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_INTERNAL_ERROR, Constants.FAILED , "Internal Server Error....!"));
			}
		}
	
	
		// getBookRecords(id) method
		@GetMapping("/getBookRecord/{id}")
		@Operation(
			    summary = "get single customer details",
			    description = "get customer record",
			    responses = {
			        @ApiResponse(responseCode = "200", description = "Customer record fetched successfully"),
			        @ApiResponse(responseCode = "400", description = "Invalid request data"),
			        @ApiResponse(responseCode = "500", description = "Internal server error")
			    }
			)
		public ResponseEntity<ResponseMessage>  getBookDataById(@PathVariable Long id) {
			logger.info("BookController getBookDataById method execution is started..!");
			BookModule book= bookService.getBookById(id);
			logger.info("BookController getBookDataById method execution is started..!");
			return ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS, "Customer Record Fetch Successfully...!",book ));
		}
		
		// getAllCustomer methods
		@GetMapping("/getAllBookRecord")
		@Operation(
			    summary = "get All book details",
			    description = "get all book record",
			    responses = {
			        @ApiResponse(responseCode = "200", description = "All book record fetched successfully"),
			        @ApiResponse(responseCode = "400", description = "Invalid request data"),
			        @ApiResponse(responseCode = "500", description = "Internal server error")
			    }
			)
		public List<BookModule> getAllCustomer() {
			return bookService.getAllBookRecord();
		}
		
		@DeleteMapping("/deletebook/{id}")
		@Operation(
			    summary = "delete single customer ",
			    description = "delete customer record",
			    responses = {
			        @ApiResponse(responseCode = "200", description = "Book record deleted successfully"),
			        @ApiResponse(responseCode = "400", description = "Invalid request data"),
			        @ApiResponse(responseCode = "500", description = "Internal server error")
			    }
			)
		public  ResponseEntity<ResponseMessage> deleteBookById(@PathVariable Long id) {
			logger.info("BookController deleteBookById method execution is started..!");
			BookModule deleteBook = bookService.deleteBooKRecordById(id);
			logger.info("BookController deleteBookById method execution is started..!");
			return  ResponseEntity.ok(new ResponseMessage(HttpURLConnection.HTTP_OK, Constants.SUCCESS, " customer record deleted....!",deleteBook  ));
		}

		// delete method
		
		@DeleteMapping("/deleteallbooks")
		@Operation(
			    summary = "delete All books records ",
			    description = "delete all books record",
			    responses = {
			        @ApiResponse(responseCode = "200", description = "All Books record deleted successfully"),
			        @ApiResponse(responseCode = "400", description = "Invalid request data"),
			        @ApiResponse(responseCode = "500", description = "Internal server error")
			    }
			)
		public String deleteAllBooksRecord() {
			logger.info("CustomerController deleteAllCustomer method execution is started..!");
			String msg = bookService.deleteAllBooks();
			logger.info("CustomerController deleteAllCustomer method execution is started..!");
			return " All customer record deleted successfully......!";
		}
		
}

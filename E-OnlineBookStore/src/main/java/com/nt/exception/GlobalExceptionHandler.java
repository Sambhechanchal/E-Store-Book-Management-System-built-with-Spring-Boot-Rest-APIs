package com.nt.exception;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nt.model.ErrorMessage;
import com.nt.utility.Constants;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CustomerIdNotFoundException.class)
	public ResponseEntity<Object> handleException(CustomerIdNotFoundException ex){
		
		// using arraylist
		List<String> list = new ArrayList<>();
		list.add("Error Details: CustomerIdNotFound");
		list.add("ErrorMessage : "+ ex.getLocalizedMessage());
		list.add("TimeStamp :"+ System.currentTimeMillis());
		ErrorMessage err = new ErrorMessage(HttpURLConnection.HTTP_BAD_REQUEST , "Invalid customer id" ,Constants.FAILUER, list);
		return ResponseEntity.ok(err);
	}
	
	@ExceptionHandler(BookIdNotFoundException.class)
	public ResponseEntity<Object> handleException(BookIdNotFoundException ex){
		
		// using HashMap
		Map<String, Object> list = new HashMap<>();
		list.put("Error Details","BookNotFoundException");
		list.put("ErrorMessage : ",ex.getLocalizedMessage());
		list.put("TimeStamp :" ,System.currentTimeMillis());
		ErrorMessage err = new ErrorMessage(HttpURLConnection.HTTP_BAD_REQUEST , "Invalid book id" ,Constants.FAILUER, list);
		return ResponseEntity.ok(err);
	}
	
	@ExceptionHandler(InternalServerException.class)
	public ResponseEntity<Object> handleException(InternalServerException ex){
		
		// using arraylist
		List<String> list = new ArrayList<>();
		list.add("Error Details: InternalServerException");
		list.add("ErrorMessage : "+ ex.getLocalizedMessage());
		list.add("TimeStamp :"+ System.currentTimeMillis());
		ErrorMessage err = new ErrorMessage(HttpURLConnection.HTTP_BAD_REQUEST , "Network failure or server down" ,Constants.FAILUER, list);
		return ResponseEntity.ok(err);
	}
	
}

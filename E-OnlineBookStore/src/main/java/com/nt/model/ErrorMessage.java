package com.nt.model;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class ErrorMessage {
	
	private Integer statusCode;
	private String status;
	private String message;
	private Map<String, Object> list;
	private List<?> list2;
	
	public ErrorMessage(Integer statusCode, String status, String message, Map<String, Object> list) {
		super();
		this.statusCode = statusCode;
		this.status = status;
		this.message = message;
		this.list = list;
	}

	public ErrorMessage(Integer statusCode, String status, String message, List<?> list2) {
		super();
		this.statusCode = statusCode;
		this.status = status;
		this.message = message;
		this.list2 = list2;
	}
	
	
	

}

package com.nt.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.nt.service.IBookService;

@RestController
public class BookController {

	 Logger logger = LogManager.getLogger(CustomerController.class);
	@Autowired
	private IBookService bookService;
	
	
}

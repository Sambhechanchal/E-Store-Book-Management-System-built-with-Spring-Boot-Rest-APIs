package com.nt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.nt.service.IBookService;

@RestController
public class BookController {

	
	@Autowired
	private IBookService bookService;
	
	
}

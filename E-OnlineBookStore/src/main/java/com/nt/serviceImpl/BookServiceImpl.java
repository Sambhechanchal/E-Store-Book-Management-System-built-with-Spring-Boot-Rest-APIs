package com.nt.serviceImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.controller.CustomerController;
import com.nt.repository.BookRepository;
import com.nt.service.IBookService;

@Service
public class BookServiceImpl implements IBookService {
	
	@Autowired
	private BookRepository bookRepo;
	
	private static final Logger logger = LogManager.getLogger(CustomerController.class);

}

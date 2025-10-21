package com.nt.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.repository.BookRepository;
import com.nt.service.IBookService;

@Service
public class BookServiceImpl implements IBookService {
	
	@Autowired
	private BookRepository bookRepo;
	
	

}

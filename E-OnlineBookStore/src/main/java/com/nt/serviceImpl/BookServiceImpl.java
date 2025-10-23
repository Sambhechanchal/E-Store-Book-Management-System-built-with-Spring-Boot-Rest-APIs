package com.nt.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.controller.CustomerController;
import com.nt.entity.BookModule;
import com.nt.entity.CustomerEntity;
import com.nt.exception.BookIdNotFoundException;
import com.nt.exception.CustomerIdNotFoundException;
import com.nt.repository.BookRepository;
import com.nt.service.IBookService;

@Service
public class BookServiceImpl implements IBookService {
	
	@Autowired
	private BookRepository bookRepo;
	
	private static final Logger logger = LogManager.getLogger(CustomerController.class);

	@Override
	public BookModule saveUpdateBookRecord(BookModule bookDto) {
logger.info("BookServiceImpl saveUpdateBookRecord method execution started..! ");
		
		logger.error("ID should not be null or empty");
		if(bookDto.getId() == null) {
			
			 return bookRepo.save(bookDto);
		}else {
		     Optional<BookModule> opt = bookRepo.findById(bookDto.getId());
		   
		     if(opt.isPresent()) {
		    	
		    	BookModule book = new BookModule();
		    	book.setBookName(opt.get().getBookName());
		    	book.setAuthor(opt.get().getAuthor());
		    	book.setBookTitle(opt.get().getBookTitle());
		    	 
		    	 bookRepo.save(book);
		    	logger.info("book data saved successfully...! ");
		     }else {
		    	 logger.error("error occur during validating optional object");
		    	 throw new BookIdNotFoundException("Invalid book Id...!");
		     }
			
		}
		logger.info("BookServiceImpl saveUpdateBookRecord method execution ended..! ");
		
		return bookDto;
	}

	@Override
	public BookModule getBookById(Long id) {
		logger.info("CustomerServiceImpl getCustomerEntity method execution started..! ");
		Optional<BookModule> opt = bookRepo.findById(id);
		if(opt.isEmpty()) {
			logger.error("error occur during validating optional object");
			throw new BookIdNotFoundException("Invalid book Id...!");
		}
		logger.info("CustomerServiceImpl getCustomerEntity method execution ended..! ");
		return opt.get();
	}

	@Override
	public List<BookModule> getAllBookRecord() {
		logger.info("BookServiceImpl getAllBookRecord method execution started..! ");
		logger.info("BookServiceImpl getAllBookRecord method execution ended..! ");
		return bookRepo.findAll();
	}

	@Override
	public BookModule deleteBooKRecordById(Long id) {
		logger.info("BookServiceImpl deleteBooKRecordById method execution started..! ");
		Optional<BookModule> opt = bookRepo.findById(id);
		if(opt.isPresent()) {
			bookRepo.deleteById(id);
			logger.info("book record deleted...! "+opt.get());
		}else {
			logger.error("error occur during validating optional object");
			throw new BookIdNotFoundException("Invalid Id");
		}
		logger.info("BookServiceImpl deleteBooKRecordById method execution ended..! ");
		return opt.get();
	}

	@Override
	public String deleteAllBooks() {
		logger.info("BookServiceImpl deleteAllBooks method execution started..! ");
		bookRepo.deleteAll();
		logger.info("BookServiceImpl deleteAllBooks method execution ended..! ");
		return "Deleted";
	}

}

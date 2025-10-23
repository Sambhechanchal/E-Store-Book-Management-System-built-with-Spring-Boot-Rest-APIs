package com.nt.service;

import java.util.List;

import com.nt.entity.BookModule;
import com.nt.entity.CustomerEntity;

public interface IBookService {

	public BookModule saveUpdateBookRecord(BookModule bookDto);

	public BookModule getBookById(Long id);

	public List<BookModule> getAllBookRecord();

	public BookModule deleteBooKRecordById(Long id);

	public String deleteAllBooks();

}

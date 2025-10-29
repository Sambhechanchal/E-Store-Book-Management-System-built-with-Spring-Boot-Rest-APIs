package com.nt.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.nt.entity.BookModule;
import com.nt.repository.BookRepository;
import com.nt.service.IBookService;

@SpringBootTest
public class BookServiceImplTest {

	
	@Autowired
	private  IBookService bookService;
	
	@MockBean
	private BookRepository bookRepo;
	
	
	@Test
	public void saveUpdateBookRecordTest() {
		
		BookModule book1 = new BookModule();
		book1.setId(1L);
		book1.setBookName("Wing of fire");
		book1.setBookTitle("Wings of fire");
		book1.setAuthor("A.P.J. Abdul kalam");
		
		Optional<BookModule> opt = Optional.of(book1);
		
		when(bookRepo.findById(opt.get().getId())).thenReturn(opt);
		
		when(bookRepo.save(any(BookModule.class))).thenReturn(book1);
		// call the service class method 
		
		BookModule record = bookService.saveUpdateBookRecord(book1);
		
		assertNotNull(record);
		assertEquals("A.P.J. Abdul kalam", record.getAuthor());
		verify(bookRepo, times(1)).save(any(BookModule.class));
	}
	
	@Test
	public void getBookByIdTest() {

		// input

		Long id = 1L;

		// output
		BookModule book1 = new BookModule();
		book1.setBookName("Wing of fire");
		book1.setBookTitle("Wings of fire");
		book1.setAuthor("A.P.J. Abdul kalam");

		Optional<BookModule> opt = Optional.of(book1);

		when(bookRepo.findById(id)).thenReturn(opt);

		// call the service class method

		BookModule record = bookService.getBookById(id);

		assertNotNull(record);
		assertEquals("A.P.J. Abdul kalam", record.getAuthor());
		verify(bookRepo, times(1)).findById(id);
	}
	
	@Test
	public void  getAllBookRecordTest() {
		
		//output
		BookModule book1 = new BookModule();
		book1.setBookName("Wing of fire");
		book1.setBookTitle("Wings of fire");
		book1.setAuthor("A.P.J. Abdul kalam");
		
		BookModule book2 = new BookModule();
		book2.setBookName("The Power of Your Subconscious Mind");
		book2.setBookTitle("Unlocking the Secrets of the Conscious and Subconscious Mind");
		book2.setAuthor("Joseph Murphy");
		
		List<BookModule> list = List.of(book1, book2);
		
		when(bookRepo.findAll()).thenReturn(list);
		
		// call service method
		
		List<BookModule> allBookRecord = bookService.getAllBookRecord();
		
		assertNotNull(allBookRecord);
		verify(bookRepo , times(1)).findAll();
		
	}
	
	@Test
	public void deleteBooKRecordByIdTest() {

		// input

		Long id = 1L;

		// output
		BookModule book1 = new BookModule();
		book1.setBookName("Wing of fire");
		book1.setBookTitle("Wings of fire");
		book1.setAuthor("A.P.J. Abdul kalam");

		Optional<BookModule> opt = Optional.of(book1);

		when(bookRepo.findById(id)).thenReturn(opt);

		// call the service class method

		BookModule record = bookService.deleteBooKRecordById(id);

		assertNotNull(record);
		assertEquals("A.P.J. Abdul kalam", record.getAuthor());
		verify(bookRepo, times(1)).deleteById(id);
	}
	
	@Test
	public void deleteAllBooksTest() {
		
		// bcoz deleteAll() method return type is void
		 doNothing().when(bookRepo).deleteAll();
		 
		 // call the service class method
		  String allbook = bookService.deleteAllBooks();
		
		  // verifying result and the custRepo method call 
		  assertNotNull(allbook);
		  verify(bookRepo, times(1)).deleteAll();
	}

}

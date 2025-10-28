package com.nt.BookModuleTest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nt.controller.BookController;
import com.nt.entity.BookModule;
import com.nt.service.IBookService;

@WebMvcTest(BookController.class)
public class BookControllerTest {

	@MockBean
	private IBookService bookService;
	
	@Autowired
	private MockMvc mock;
	
	@Test
	public void saveOrUpdateBookRecordTest()throws Exception {
		
		BookModule book1 = new BookModule();
		book1.setBookName("Wing of fire");
		book1.setBookTitle("Wings of fire");
		book1.setAuthor("A.P.J. Abdul kalam");
		
		BookModule book2 = new BookModule();
		book2.setBookName("The Power of Your Subconscious Mind");
		book2.setBookTitle("Unlocking the Secrets of the Conscious and Subconscious Mind");
		book2.setAuthor("Joseph Murphy");
		
		
		when(bookService.saveUpdateBookRecord(book1)).thenReturn(book2);
		
		   mock.perform(
		            post("/createOrupdateBookRecord")   
		                .contentType(MediaType.APPLICATION_JSON)
		                .accept(MediaType.APPLICATION_JSON)
		                .content(new ObjectMapper().writeValueAsString(book1))
		        )
		        .andExpect(status().isOk())  
		        .andExpect(content().contentType(MediaType.APPLICATION_JSON)); 
		}
		
	@Test
	public void getBookDataByIdTest()throws Exception {
		
		Long id = 1L;
		
		BookModule book1 = new BookModule();
		book1.setBookName("Wing of fire");
		book1.setBookTitle("Wings of fire");
		book1.setAuthor("A.P.J. Abdul kalam");
		
		when(bookService.getBookById(id)).thenReturn(book1);
		
		mock.perform(
				
				get("/getBookRecord/{id}",id)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(id))
				
				).andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON));
		
	}
	
	@Test
	public void getAllCustomerTest()throws Exception {
		
	
		
		BookModule book1 = new BookModule();
		book1.setBookName("Wing of fire");
		book1.setBookTitle("Wings of fire");
		book1.setAuthor("A.P.J. Abdul kalam");
		
		BookModule book2 = new BookModule();
		book2.setBookName("The Power of Your Subconscious Mind");
		book2.setBookTitle("Unlocking the Secrets of the Conscious and Subconscious Mind");
		book2.setAuthor("Joseph Murphy");
		
		List<BookModule> list= List.of(book1, book2);
		
		when(bookService.getAllBookRecord()).thenReturn(list);
		
		mock.perform(
				
				get("/getAllBookRecord")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(list))
				
				).andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON));
		
	}
	
	@Test
	public void deleteBookByIdTest()throws Exception {
		
		Long id = 1L;
		
		BookModule book1 = new BookModule();
		book1.setBookName("Wing of fire");
		book1.setBookTitle("Wings of fire");
		book1.setAuthor("A.P.J. Abdul kalam");
		
		when(bookService.deleteBooKRecordById(id)).thenReturn(book1);
		
		mock.perform(
				
				delete("/deletebook/{id}",id)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(id))
				
				).andExpect(status().isOk())
					.andExpect(content().contentType(MediaType.APPLICATION_JSON));
		
	}
	
	
}

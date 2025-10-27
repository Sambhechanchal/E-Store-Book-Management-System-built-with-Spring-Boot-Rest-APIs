package com.nt.CustomerControllerTest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nt.controller.CustomerController;
import com.nt.entity.CustomerEntity;
import com.nt.service.ICustomerService;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
	
	@MockBean
	private ICustomerService custService;
	
	@Autowired
    private MockMvc mock;
	
	@Test
	public void saveCustomerTest()throws Exception {
		
		
		CustomerEntity cust = new CustomerEntity();
		cust.setName("chanchal");
		cust.setEmail("C@gmail.com");
		
		
		CustomerEntity cust2 = new CustomerEntity();
		cust2.setName("chanchuu");
		cust2.setEmail("CS@gmail.com");
		
		when(custService.saveCustomerRecord(cust)).thenReturn(cust2);
		
		mock.perform(
				
				post("/savecustomer")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(cust2))
				).andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON));
		
		
		
	}
	
	@Test
	public void createOrupdateCustomerRecordTest() throws Exception {

		CustomerEntity cust = new CustomerEntity();
		cust.setName("achal");
		cust.setEmail("A@gmail.com");

		CustomerEntity cust2 = new CustomerEntity();
		cust2.setName("chanchuu");
		cust2.setEmail("AS@gmail.com");

		when(custService.updateCustomerRecord(cust)).thenReturn(cust2);

		mock.perform(

				post("/createOrupdateCustomerRecord").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(cust2)))
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));

	}
	
	@Test
	public void getCustomerRecordTest()throws Exception {
		
		Long id = 1L;
		CustomerEntity cust = new CustomerEntity();
		cust.setName("chanchal");
		cust.setEmail("C@gmail.com");
		
		when(custService.getCustomerEntity(id)).thenReturn(cust);
		
		mock.perform(
				
				get("/getCustomerRecord/{id}",id)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(id))
				).andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON));
		
	}
	
	

	@Test
	public void getAllCustomerTest()throws Exception {
		
		CustomerEntity cust = new CustomerEntity();
		cust.setName("chanchal");
		cust.setEmail("C@gmail.com");
		
		CustomerEntity cust2 = new CustomerEntity();
		cust2.setName("chanchal");
		cust2.setEmail("C@gmail.com");
		
		List<CustomerEntity> list = List.of(cust, cust2);
		
		when(custService.getAllCustomerEntity()).thenReturn(list);
		
		mock.perform(
				
				get("/getAllCustomerRecord")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(list))
				).andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON));
		
	}
	

}

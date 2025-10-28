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

import com.nt.entity.CustomerEntity;
import com.nt.repository.CustomerRepo;
import com.nt.service.ICustomerService;

@SpringBootTest
public class CustomerServiceImplTest {
	
	@Autowired
	private ICustomerService custService;
	
	
	@MockBean
	private CustomerRepo custRepo;
	
	@Test
	public void saveCustomerRecordTest() {
		
		// input
		CustomerEntity input = new CustomerEntity();
		input.setName("chanchal");
		input.setEmail("S@gmail.com");
		
		//output
		CustomerEntity output = new CustomerEntity();
		output.setName("chanchal");
		output.setEmail("S@gmail.com");
		
		// verifying method working as expected or not 
		when(custRepo.save(any(CustomerEntity.class))).thenReturn(output);
	
		// calling custService method
		
		CustomerEntity record = custService.saveCustomerRecord(input);
		
		// checking some condition
		
		assertNotNull(record);
		assertEquals("S@gmail.com", record.getEmail());
		verify(custRepo, times(1)).save(any(CustomerEntity.class));
		
		
		
	}
	
	@Test
	public void updateCustomerRecordTest() {

		// input

		CustomerEntity input = new CustomerEntity();
		input.setName("chanchal");
		input.setEmail("S@gmail.com");
		
		// output for the 
		Optional<CustomerEntity> opt = Optional.of(input);
		
		//when(custRepo.findById(opt.get().getId())).thenReturn(opt);
		
		when(custRepo.save(any(CustomerEntity.class))).thenReturn(input);
		
		// call the service class method
		
		CustomerEntity customerRecord = custService.updateCustomerRecord(input);
		assertNotNull(customerRecord);
		assertEquals("S@gmail.com",customerRecord.getEmail());
		verify(custRepo, times(1)).save(any(CustomerEntity.class));
	}
	
	@Test
	public void getCustomerEntityTest() {
		

		// input
		Long id =1L;
		
		//output
		CustomerEntity input = new CustomerEntity();
		input.setId(id);
		input.setName("chanchal");
		input.setEmail("S@gmail.com");
		
		
		// output of the findbyid method
		Optional<CustomerEntity> opt = Optional.of(input);
		
		when(custRepo.findById(opt.get().getId())).thenReturn(opt);
		
		// call the service class method
		
				CustomerEntity customerRecord = custService.getCustomerEntity(id);
				assertNotNull(customerRecord);
				assertEquals("S@gmail.com",customerRecord.getEmail());
				verify(custRepo, times(1)).findById(id);
		
	}
	
	@Test
	public void getAllCustomerEntityTest() {

		// output
		CustomerEntity input1 = new CustomerEntity();
		input1.setName("chanchal");
		input1.setEmail("S@gmail.com");

		CustomerEntity input2 = new CustomerEntity();
		input2.setName("chanchal");
		input2.setEmail("S@gmail.com");
		
		List<CustomerEntity> list = List.of(input1, input2);
		
		when(custRepo.findAll()).thenReturn(list);
		
		// call the service class method
		
		List<CustomerEntity> entity = custService.getAllCustomerEntity();
		assertNotNull(entity);
		verify(custRepo, times(1)).findAll();
		
	}
	
	
	@Test
	public void deleteCustomerTest() {

		// input
		Long id = 1L;

		// output
		CustomerEntity input = new CustomerEntity();
		input.setId(id);
		input.setName("chanchal");
		input.setEmail("S@gmail.com");

		// output of the findbyid method
		Optional<CustomerEntity> opt = Optional.of(input);

		when(custRepo.findById(opt.get().getId())).thenReturn(opt);

		// call the service class method

		CustomerEntity customerRecord = custService.deleteCustomer(id);
		assertNotNull(customerRecord);
		assertEquals("S@gmail.com", customerRecord.getEmail());
		verify(custRepo, times(1)).deleteById(id);

	}
	
	
	@Test
	public void deleteAllCustomerTest() {
		
		// bcoz deleteAll() method return type is void
		 doNothing().when(custRepo).deleteAll();
		 
		 // call the service class method
		  String allCustomer = custService.deleteAllCustomer();
		
		  // verifying result and the custRepo method call 
		  assertNotNull(allCustomer);
		  verify(custRepo, times(1)).deleteAll();
	}

}

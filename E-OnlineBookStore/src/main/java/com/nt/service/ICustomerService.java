package com.nt.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.nt.entity.CustomerEntity;

public interface ICustomerService {

	public CustomerEntity saveCustomerRecord(CustomerEntity cust);

	public CustomerEntity updateCustomerRecord(CustomerEntity cust);

	public CustomerEntity getCustomerEntity(Long id);

	public Iterable<CustomerEntity> getAllCustomerEntity();

	public CustomerEntity deleteCustomer(Long id);

	public String deleteAllCustomer();

	public Page<CustomerEntity> getCustomerByPage(int pagenumber, int pagesize, String field, String sortOrder);
	
	

}

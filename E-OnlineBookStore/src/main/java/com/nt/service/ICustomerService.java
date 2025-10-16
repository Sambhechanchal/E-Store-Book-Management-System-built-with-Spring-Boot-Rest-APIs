package com.nt.service;

import com.nt.entity.CustomerEntity;

public interface ICustomerService {

	public CustomerEntity saveCustomerRecord(CustomerEntity cust);

	public CustomerEntity updateCustomerRecord(CustomerEntity cust);

	public CustomerEntity getCustomerEntity(Long id);

	public Iterable<CustomerEntity> getAllCustomerEntity();

	public CustomerEntity deleteCustomer(Long id);

	public String deleteAllCustomer();
	
	

}

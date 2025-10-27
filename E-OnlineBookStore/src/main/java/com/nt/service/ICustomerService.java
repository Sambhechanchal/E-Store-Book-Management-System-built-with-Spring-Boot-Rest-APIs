package com.nt.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.nt.entity.CustomerEntity;

public interface ICustomerService {

	public CustomerEntity saveCustomerRecord(CustomerEntity cust);

	public CustomerEntity updateCustomerRecord(CustomerEntity cust);

	public CustomerEntity getCustomerEntity(Long id);

	public List<CustomerEntity>  getAllCustomerEntity();

	public CustomerEntity deleteCustomer(Long id);

	public String deleteAllCustomer();

	public Page<CustomerEntity> getCustomerByPage(int pagenumber, int pagesize, String field, String sortOrder);
	
	

}

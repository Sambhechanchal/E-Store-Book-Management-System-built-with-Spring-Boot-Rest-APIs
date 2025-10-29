package com.nt.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.nt.controller.CustomerController;
import com.nt.entity.CustomerEntity;
import com.nt.entity.mongo.CustomerEntityMongo;
import com.nt.exception.CustomerIdNotFoundException;
import com.nt.repository.CustomerRepo;
import com.nt.repository.mongo.CustomerMongoRepo;
import com.nt.service.ICustomerService;

//@Profile({"dev"})
@Service
//@Profile("qa")
public class CustomerServiceImpl  implements ICustomerService{
	
	@Autowired
	private CustomerRepo custRepo;
	
	@Autowired
	private CustomerMongoRepo custMongoRepo;
	
//	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	private static final Logger logger = LogManager.getLogger(CustomerController.class);
	
	@Override
	public CustomerEntity saveCustomerRecord(CustomerEntity cust) {
		logger.info("CustomerServiceImpl saveCustomerRecord method execution started..! ");
		 CustomerEntity save = custRepo.save(cust);
		 
		 // insert customer object to mongo db
		 CustomerEntityMongo custMongo = new CustomerEntityMongo();
		 custMongo.setName(cust.getName());
		 custMongo.setEmail(cust.getEmail());
		 
		 custMongoRepo.save(custMongo);
		 logger.info("CustomerServiceImpl saveCustomerRecord method execution ended...! ");
		return save;
	}

	@Override
	public CustomerEntity updateCustomerRecord(CustomerEntity cust) {
		logger.info("CustomerServiceImpl updateCustomerRecord method execution started..! ");
		
		logger.error("ID should not be null or empty");
		if(cust.getId() == null) {
			
			 return custRepo.save(cust);
		}else {
		     Optional<CustomerEntity> opt = custRepo.findById(cust.getId());
		   
		     if(opt.isPresent()) {
		    	
		    	 CustomerEntity customer = opt.get();
		    	 customer.setEmail(cust.getEmail());
		    	 customer.setName(cust.getName());
		    	 
		    	custRepo.save(customer);
		    	logger.info("Customer record saved successfully...! ");
		     }else {
		    	 logger.error("error occur during validating optional object");
		    	 throw new CustomerIdNotFoundException("customer id not found");
		     }
			
		}
		logger.info("CustomerServiceImpl updateCustomerRecord method execution ended..! ");
		return cust;
		
	}

	@Override
	public CustomerEntity getCustomerEntity(Long id) {
		logger.info("CustomerServiceImpl getCustomerEntity method execution started..! ");
		Optional<CustomerEntity> opt = custRepo.findById(id);
		if(opt.isEmpty()) {
			logger.error("error occur during validating optional object");
			throw new CustomerIdNotFoundException("Customer ID not found");
		}
		logger.info("CustomerServiceImpl getCustomerEntity method execution ended..! ");
		return opt.get();
	}

	@Override
	public List<CustomerEntity> getAllCustomerEntity() {
		logger.info("CustomerServiceImpl getAllCustomerEntity method execution started..! ");
		logger.info("CustomerServiceImpl getAllCustomerEntity method execution ended..! ");
		return custRepo.findAll();
	}

	@Override
	public CustomerEntity deleteCustomer(Long id) {
		logger.info("CustomerServiceImpl deleteCustomer method execution started..! ");
		Optional<CustomerEntity> opt = custRepo.findById(id);
		if(opt.isPresent()) {
			custRepo.deleteById(id);
			logger.info("customer record deleted by id..! "+id);
		}else {
			logger.error("error occur during validating optional object");
			throw new CustomerIdNotFoundException("Invalid Id");
		}
		logger.info("CustomerServiceImpl deleteCustomer method execution ended..! ");
		return opt.get();
	}

	@Override
	public String deleteAllCustomer() {
		logger.info("CustomerServiceImpl deleteAllCustomer method execution started..! ");
		custRepo.deleteAll();
		logger.info("CustomerServiceImpl deleteAllCustomer method execution ended..! ");
		return "Deleted";
	}

	@Override
	public Page<CustomerEntity> getCustomerByPage(int pagenumber, int pagesize, String field, String sortOrder) {
		logger.info("CustomerServiceImpl getCustomerByPage method execution started..! ");
		Sort sort = Sort.by(sortOrder.equalsIgnoreCase("asc")? Sort.Direction.ASC: Sort.Direction.DESC, field);
		
		PageRequest pageRequest = PageRequest.of(pagenumber, pagesize, sort);
		logger.info("CustomerServiceImpl getCustomerByPage method execution ended..! ");
		return custRepo.findAll(pageRequest);
	}

}

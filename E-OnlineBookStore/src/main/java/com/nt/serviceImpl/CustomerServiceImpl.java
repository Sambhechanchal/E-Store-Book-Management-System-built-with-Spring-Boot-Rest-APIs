package com.nt.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.nt.entity.CustomerEntity;
import com.nt.exception.CustomerIdNotFoundException;
import com.nt.repository.CustomerRepo;
import com.nt.service.ICustomerService;

@Service
//@Profile("qa")
public class CustomerServiceImpl  implements ICustomerService{
	
	@Autowired
	private CustomerRepo custRepo;

	@Override
	public CustomerEntity saveCustomerRecord(CustomerEntity cust) {
		 CustomerEntity save = custRepo.save(cust);
		return save;
	}

	@Override
	public CustomerEntity updateCustomerRecord(CustomerEntity cust) {
		
		if(cust.getId() == null) {
			 return custRepo.save(cust);
		}else {
		     Optional<CustomerEntity> opt = custRepo.findById(cust.getId());
		     if(opt.isPresent()) {
		    	 CustomerEntity customer = opt.get();
		    	 customer.setEmail(cust.getEmail());
		    	 customer.setName(cust.getName());
		    	 
		    	custRepo.save(customer);
		     }else {
		    	 throw new CustomerIdNotFoundException("customer id not found");
		     }
			
		}
		return cust;
		
	}

	@Override
	public CustomerEntity getCustomerEntity(Long id) {
		Optional<CustomerEntity> opt = custRepo.findById(id);
		if(opt.isEmpty()) {
			throw new CustomerIdNotFoundException("Customer ID not found");
		}
		return opt.get();
	}

	@Override
	public Iterable<CustomerEntity> getAllCustomerEntity() {
		
		return custRepo.findAll();
	}

	@Override
	public CustomerEntity deleteCustomer(Long id) {
		Optional<CustomerEntity> opt = custRepo.findById(id);
		if(opt.isPresent()) {
			custRepo.deleteById(id);
		}else {
			throw new CustomerIdNotFoundException("Invalid Id");
		}
		return opt.get();
	}

	@Override
	public String deleteAllCustomer() {
		custRepo.deleteAll();
		return "Deleted";
	}

	@Override
	public Page<CustomerEntity> getCustomerByPage(int pagenumber, int pagesize, String field, String sortOrder) {
		
		Sort sort = Sort.by(sortOrder.equalsIgnoreCase("asc")? Sort.Direction.ASC: Sort.Direction.DESC, field);
		
		PageRequest pageRequest = PageRequest.of(pagenumber, pagesize, sort);
		
		return custRepo.findAll(pageRequest);
	}

}

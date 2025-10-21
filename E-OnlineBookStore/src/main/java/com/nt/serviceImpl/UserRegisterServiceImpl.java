package com.nt.serviceImpl;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nt.entity.FileEntity;
import com.nt.entity.UserRegister;
import com.nt.model.UserRequest;
import com.nt.model.UserRequest3Dto;
import com.nt.model.UserRequestDto;
import com.nt.repository.FileRepository;
import com.nt.repository.UserRegisterRepo;
import com.nt.service.IUserRegisterService;

@Service
public class UserRegisterServiceImpl implements IUserRegisterService {

	@Autowired
	private UserRegisterRepo userRepo;
	
	@Autowired
	private  FileRepository fileRepo;
	
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(FileMngtServiceImpl.class);
	
	@Override
	public UserRegister insertUserRegister(UserRequestDto userDto) {
		logger.info("UserRegisterServiceImpl insertUserRegister method execution is started...!");
		UserRegister user = new UserRegister();
		
		try {
			logger.debug("trying to set the new value to existing userRegister object");
			user.setFirstName(userDto.getFirstName());
			user.setLastName(userDto.getLastName());
			user.setEmail(userDto.getEmail());
			// encrypting the password before storing into database
			user.setPassword(Base64.getEncoder().encodeToString(userDto.getPassword().getBytes()));
			user.setContactId(userDto.getContactId());
			
			userRepo.save(user);
			logger.info("insertUserRegister method inserted data successfully..!");
			
		}catch(Exception e) {
			logger.error("Error occur during inserting data to database.... "+e.getMessage());
			e.printStackTrace();
		}
		logger.info("insertUserRegister method executed.....");
		return user;
	}

	@Override
	public UserRequest getUserDetails(int id) {
		logger.info("UserRegisterServiceImpl getUserDetails method execution is started...!");
		Optional<UserRegister> opt = userRepo.findById(id);
		// we need to send the user request object here so we are setting the userRequest object values from the database opt object
		UserRequest user = new UserRequest();
		user.setFirstName(opt.get().getFirstName());
		user.setLastName(opt.get().getLastName());
		
		logger.info("UserRegisterServiceImpl getUserDetails method execution ended...!");
		return user;
	}

	@Override
	public UserRegister checkUserDetails(UserRequestDto userRequestDto) {
		logger.info("UserRegisterServiceImpl checkUserDetails method execution is started...!");
	    try {
	    	logger.debug("trying to set the new value to existing userRegister object");
	        UserRegister user = userRepo.findByEmail(userRequestDto.getEmail());
	        System.out.println(user);
	        logger.warn("user null or not validating...");
	        if (user != null) {
	            String pass = new String(Base64.getDecoder().decode(user.getPassword()));
	            logger.warn("validating user password");
	            if (pass.equals(userRequestDto.getPassword())) {
	            	logger.info("UserRegisterServiceImpl checkUserDetails method execution ended");
//	            	System.out.println("service -- userRegister " +user);
	                return user;
	            }
	        }
	    } catch (Exception e) {
	    	logger.error("Error occurs in checkUserDetails "+ e.getMessage());
	        e.printStackTrace();
	    }
	    return null ; 
	}

	@Override
	public UserRegister getUserDetails3(Integer id) {
		logger.info("UserRegisterServiceImpl getUserDetails3 method execution is started...!");
   Optional<UserRegister> opt = userRepo.findById(id);
   logger.debug("trying to set the new value to existing userRegister object");
		//UserRequest3Dto user = new UserRequest3Dto();
   UserRegister user = new UserRegister();
		user.setFirstName(opt.get().getFirstName());
		user.setLastName(opt.get().getLastName());
		user.setEmail(opt.get().getEmail());
		userRepo.save(user);
		logger.info("UserRegisterServiceImpl getUserDetails3 method execution ended...!");
		return user;
	}

	@Override
	public UserRegister uploadMultiUserRegister(UserRequestDto userObject, MultipartFile[] files) {
		logger.info("UserRegisterServiceImpl uploadMultiUserRegister method execution is started...!");
		UserRegister user = new UserRegister();
		try {
			logger.debug("trying to set user details to exizting record ");
			user.setFirstName(userObject.getFirstName());
			user.setLastName(userObject.getLastName());
			user.setEmail(userObject.getEmail());
			// encrypting the password before storing into database
			user.setPassword(Base64.getEncoder().encodeToString(userObject.getPassword().getBytes()));
			user.setContactId(userObject.getContactId());
			
			userRepo.save(user);
			logger.info("User object saved successfuly");
			logger.warn("validating file array here...!");
			if(files != null && files.length>0) {
				for(MultipartFile file: files) {
					FileEntity fss = new FileEntity();
					fss.setFileName(file.getOriginalFilename());
					fss.setFileType(file.getContentType());
					fss.setData(file.getBytes());
					fileRepo.save(fss);
					logger.info("UserRegisterServiceImpl uploadMultiUserRegister method file is saved...!");
				}
			}
		}catch(Exception e) {
			logger.info("Error occur in uploadMultiUserRegister() "+e.getMessage());
			e.printStackTrace();
		}
		logger.info("UserRegisterServiceImpl uploadMultiUserRegister method execution ended...!");
		return user;
	}

	@Override
	@Cacheable("userDetails")
	public List<UserRegister> getAllUserRegisterData() {
		logger.info("UserRegisterServiceImpl getAllUserRegisterData method execution is started...!");
		logger.info("UserRegisterServiceImpl getAllUserRegisterData method execution is ended...!");
		return userRepo.findAll();
	}


}

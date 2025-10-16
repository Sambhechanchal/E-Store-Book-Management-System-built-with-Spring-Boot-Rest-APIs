package com.nt.serviceImpl;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

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
	
	@Override
	public UserRegister insertUserRegister(UserRequestDto userDto) {
		UserRegister user = new UserRegister();
		
		try {
			user.setFirstName(userDto.getFirstName());
			user.setLastName(userDto.getLastName());
			user.setEmail(userDto.getEmail());
			// encrypting the password before storing into database
			user.setPassword(Base64.getEncoder().encodeToString(userDto.getPassword().getBytes()));
			user.setContactId(userDto.getContactId());
			
			userRepo.save(user);
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public UserRequest getUserDetails(int id) {
		Optional<UserRegister> opt = userRepo.findById(id);
		
		UserRequest user = new UserRequest();
		user.setFirstName(opt.get().getFirstName());
		user.setLastName(opt.get().getLastName());
		return user;
	}

	@Override
	public UserRegister checkUserDetails(UserRequestDto userRequestDto) {
	    try {
	    	
	        UserRegister user = userRepo.findByEmail(userRequestDto.getEmail());
	        System.out.println(user);
	        if (user != null) {
	            String pass = new String(Base64.getDecoder().decode(user.getPassword()));
	            
	            if (pass.equals(userRequestDto.getPassword())) {
//	            	System.out.println("service -- userRegister " +user);
	                return user;
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null ; 
	}

	@Override
	public UserRequest3Dto getUserDetails3(Integer id) {
   Optional<UserRegister> opt = userRepo.findById(id);
		
		UserRequest3Dto user = new UserRequest3Dto();
		user.setFirstName(opt.get().getFirstName());
		user.setLastName(opt.get().getLastName());
		user.setEmail(opt.get().getEmail());
		return user;
	}

	@Override
	public UserRegister uploadMultiUserRegister(UserRequestDto userObject, MultipartFile[] files) {
		UserRegister user = new UserRegister();
		try {
			user.setFirstName(userObject.getFirstName());
			user.setLastName(userObject.getLastName());
			user.setEmail(userObject.getEmail());
			// encrypting the password before storing into database
			user.setPassword(Base64.getEncoder().encodeToString(userObject.getPassword().getBytes()));
			user.setContactId(userObject.getContactId());
			
			userRepo.save(user);
			if(files != null && files.length>0) {
				for(MultipartFile file: files) {
					FileEntity fss = new FileEntity();
					fss.setFileName(file.getOriginalFilename());
					fss.setFileType(file.getContentType());
					fss.setData(file.getBytes());
					fileRepo.save(fss);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	@Cacheable("userDetails")
	public List<UserRegister> getAllUserRegisterData() {
		return userRepo.findAll();
	}


}

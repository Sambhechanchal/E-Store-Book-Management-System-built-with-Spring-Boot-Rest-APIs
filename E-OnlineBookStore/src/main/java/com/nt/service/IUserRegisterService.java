package com.nt.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.nt.entity.UserRegister;
import com.nt.model.UserRequest;
import com.nt.model.UserRequest3Dto;
import com.nt.model.UserRequestDto;

public interface IUserRegisterService {

	public UserRegister insertUserRegister(UserRequestDto userDto);

	public UserRequest getUserDetails(int id);

	public UserRegister checkUserDetails(UserRequestDto userRequestDto);

	public UserRegister getUserDetails3(Integer id);

	public UserRegister uploadMultiUserRegister(UserRequestDto userObject, MultipartFile[] files);

	public List<UserRegister> getAllUserRegisterData();

	
}

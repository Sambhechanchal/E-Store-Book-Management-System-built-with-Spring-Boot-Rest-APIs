package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.UserRegister;

public interface UserRegisterRepo extends JpaRepository<UserRegister, Integer>{

	public UserRegister findByEmail(String email);
}

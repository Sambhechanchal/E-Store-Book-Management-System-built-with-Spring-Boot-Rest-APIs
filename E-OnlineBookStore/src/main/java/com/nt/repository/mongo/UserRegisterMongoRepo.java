package com.nt.repository.mongo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.nt.entity.UserRegister;
import com.nt.entity.mongo.UserRegisterMongo;

public interface UserRegisterMongoRepo extends MongoRepository<UserRegisterMongo, String>{

	public UserRegister findByEmail(String email);
}

package com.nt.repository.mongo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import com.nt.entity.mongo.CustomerEntityMongo;
import com.nt.entity.mongo.UserRegisterMongo;

@Repository
public interface CustomerMongoRepo extends MongoRepository<CustomerEntityMongo, String>{

}

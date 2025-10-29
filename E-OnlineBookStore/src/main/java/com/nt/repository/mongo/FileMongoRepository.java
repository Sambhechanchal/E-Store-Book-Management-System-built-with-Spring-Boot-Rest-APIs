package com.nt.repository.mongo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nt.entity.FileEntity;
import com.nt.entity.mongo.FileEntityMongo;
import com.nt.entity.mongo.UserRegisterMongo;

@Repository
public interface FileMongoRepository extends MongoRepository<FileEntityMongo, String>{

}

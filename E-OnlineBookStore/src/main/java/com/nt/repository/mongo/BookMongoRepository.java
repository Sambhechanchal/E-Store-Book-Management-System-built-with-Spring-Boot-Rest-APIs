package com.nt.repository.mongo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nt.entity.BookModule;
import com.nt.entity.mongo.BookModuleMongo;

@Repository
public interface BookMongoRepository extends MongoRepository<BookModuleMongo, String>{

}

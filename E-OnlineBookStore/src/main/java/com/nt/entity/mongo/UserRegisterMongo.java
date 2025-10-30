package com.nt.entity.mongo;


import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;



@Document(collection="user_register")
@Data
@AllArgsConstructor  
//@NoArgsConstructor

public class UserRegisterMongo {
	
	@Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
	private long contactId;

	public UserRegisterMongo(){
		System.out.println("userRegisterMongoBD object created");
	}
  
}

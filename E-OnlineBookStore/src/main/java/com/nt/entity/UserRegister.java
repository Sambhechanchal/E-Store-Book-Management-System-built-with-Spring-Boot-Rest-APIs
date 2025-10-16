package com.nt.entity;


import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


@Data
@Entity
@Table(name="register")
@AllArgsConstructor  
@NoArgsConstructor
@RequiredArgsConstructor
public class UserRegister {
	
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Integer id;
	
	@NonNull
	@Column(name= "First_Name")
    private String firstName;

	@NonNull
	@Column(name= "Last_Name")
    private String lastName;

	@NonNull
	@Column(name= "Email")
    private String email;

	@NonNull
	@Column(name= "Password")
    private String password;
	@NonNull
	@Column(name= "Contact_Id")
	 private long contactId;

	@CreationTimestamp
	@Column(name="Creation_Time")
	private LocalDateTime creationTime;
	
	@UpdateTimestamp
	@Column(name="Update_Time")
	private LocalDateTime updateTime;
  
}

package com.nt.entity.mongo;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Document(collection ="Customer_tab")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEntityMongo {
	
	@Id
	private String id;
	private String name;
	private String email;
	
}

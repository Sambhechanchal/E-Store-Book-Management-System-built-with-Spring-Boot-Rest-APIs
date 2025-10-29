package com.nt.entity.mongo;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Document(collection="File_Table")
@AllArgsConstructor
@NoArgsConstructor
public class FileEntityMongo {

	@Id
	private String id;
	private String fileName;
	private String fileType;
	private byte[] data;
	
	}

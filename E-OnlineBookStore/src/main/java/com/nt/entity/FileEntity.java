package com.nt.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

@Entity
@Data
@Table(name="File_Table")
@AllArgsConstructor
@NoArgsConstructor
public class FileEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="fileName")
	private String fileName;
	
	@Column(name="fileType")
	private String fileType;
	
	@Lob
	@Column(name="fileData")
	private byte[] data;
	
	@CreationTimestamp
	@Column(name="Creation_Time")
	private LocalDateTime creationTime;
	
	@UpdateTimestamp
	@Column(name="Update_Time")
	private LocalDateTime updateTime;
	
}

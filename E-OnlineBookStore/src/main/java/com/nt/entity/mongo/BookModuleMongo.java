package com.nt.entity.mongo;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection="book_Module")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookModuleMongo {

	@Id
	private String id;
	private String bookName;
	private String bookTitle;
	private String author;
	
}

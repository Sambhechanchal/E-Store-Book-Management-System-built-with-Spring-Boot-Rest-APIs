package com.nt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
	    info = @Info(
	        title = "E-OnlineBookStore",
	        version = "1.0",
	        description = "API documentation for E-OnlineBookStore application"
	    )
	)
@EnableCaching
public class EOnlineBookStore {

	public static void main(String[] args) {
		SpringApplication.run(EOnlineBookStore.class, args);
	}
	


}

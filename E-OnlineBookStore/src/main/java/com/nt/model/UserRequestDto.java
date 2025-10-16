package com.nt.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.NonNull;

@Data

public class UserRequestDto {
	

	
	@Schema(description = "Enter First name ")
    private String firstName;

	@Schema(description = "Enter Last name ")
    private String lastName;
	@Schema(description = "Enter Email", required = true)
    private String email;
	@Schema(description = "Enter Password ", required = true)
    private String password;
    @Schema(description = "Enter Contact_ID ")
	 private long contactId;

  

}

package com.microservices.microservices.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Schema(
        name = "Customer",
        description =  "Schema to hold Customer and Account information"
)
public class CustomerDto {

     @Schema(
             description =  "Name of the customer"
     )

     @NotEmpty(message = "Name can not be null or empty")
     @Size(min=5, max = 50 , message = "Th length of the customer name should be between 5 and 30 characters.")
     private String name;

     @NotEmpty(message = "Email can not be null or empty")
     @Email(message = "Email address should be a valid value")
     private String email;

     @Pattern(regexp = "(^$|[0-9]{10})" , message = "Mobile number must be 10 digits")
     private String mobileNumber;

     private AccountsDto accountsDto;
}

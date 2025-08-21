package com.example.registry_service.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private long id;
    private String name;
    @Email(message = "Please Enter A Valid Email")
    private String email;
    @Size(min = 8, max = 18, message = "Password Must Be Between 8 And 18 Letter")
    private String pass;
    @Pattern(
            regexp = "^(010|011|012|015)[0-9]{8}$",
            message = "Invalid Egyptian phone number"
    )
    private String phoneNumber;
    private Roles role;
}

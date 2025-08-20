package com.example.registry_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Email(message = "Please Enter A Valid Email")
    private String email;
//    @Size(min = 8, max = 18, message = "Password Must Be Between 8 And 18 Letter")
    private String pass;
    @Pattern(
            regexp = "^(010|011|012|015)[0-9]{8}$",
            message = "Invalid Egyptian phone number"
    )
    private String phoneNumber;
}

package com.example.registry_service.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private long id;
    private String name;
    private String email;
    private String pass;
    private String phoneNumber;
}

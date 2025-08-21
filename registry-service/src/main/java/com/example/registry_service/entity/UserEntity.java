package com.example.registry_service.entity;

import com.example.registry_service.dto.Roles;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Entity
@Setter
@Getter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    private String pass;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Roles role;
}

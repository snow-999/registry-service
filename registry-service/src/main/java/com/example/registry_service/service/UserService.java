package com.example.registry_service.service;

import com.example.registry_service.dto.UserDTO;

public interface UserService {
    UserDTO login(UserDTO userDTO);
    UserDTO signup(UserDTO userDTO);
}

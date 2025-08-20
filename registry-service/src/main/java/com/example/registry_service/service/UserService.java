package com.example.registry_service.service;

import com.example.registry_service.dto.TokenModel;
import com.example.registry_service.dto.UserDTO;
import jakarta.servlet.http.HttpServletResponse;

public interface UserService {
    TokenModel login(UserDTO userModel, HttpServletResponse response);
    UserDTO signup(UserDTO userDTO);
}

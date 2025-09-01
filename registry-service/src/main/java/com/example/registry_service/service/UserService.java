package com.example.registry_service.service;

import com.example.registry_service.dto.Roles;
import com.example.registry_service.dto.TokenModel;
import com.example.registry_service.dto.UserDTO;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public interface UserService {
    TokenModel login(UserDTO userModel, HttpServletResponse response);
    UserDTO signup(UserDTO userDTO);
    void deleteUser(long userId);
    UserDTO getMyUserById(long userId);
    UserDTO updateUser(UserDTO userDTO, long userId);
    List<UserDTO> getAllUsers();
    UserDTO addNewAdmin(Roles roleName, long userId);
    UserDTO getUserByEmail(String email);
}

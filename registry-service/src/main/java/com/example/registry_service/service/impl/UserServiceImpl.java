package com.example.registry_service.service.impl;


import com.example.registry_service.controller.UserController;
import com.example.registry_service.converter.UserConverter;
import com.example.registry_service.dto.UserDTO;
import com.example.registry_service.entity.UserEntity;
import com.example.registry_service.repository.UserRepository;
import com.example.registry_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserConverter userConverter;

    @Override
    public UserDTO login(UserDTO userDTO) {
        return null;
    }

    @Override
    public UserDTO signup(UserDTO userDTO) {
        UserEntity user = userConverter.convertUserDTOToEntity(userDTO);
        user = userRepository.save(user);
        userDTO = userConverter.convertUserEntityToDTO(user);
        return userDTO;
    }
}

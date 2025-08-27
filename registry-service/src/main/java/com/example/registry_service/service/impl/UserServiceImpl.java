package com.example.registry_service.service.impl;


import com.example.registry_service.converter.UserConverter;
import com.example.registry_service.dto.TokenModel;
import com.example.registry_service.dto.UserDTO;
import com.example.registry_service.entity.UserEntity;

import com.example.registry_service.exception.UserNotFound;
import org.springframework.security.authentication.AuthenticationManager;
import com.example.registry_service.repository.UserRepository;
import com.example.registry_service.service.JWTService;
import com.example.registry_service.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDTO signup(UserDTO userDTO) {
        if (userDTO.getName() == null || userDTO.getEmail()== null || userDTO.getPass()== null) {
            throw new IllegalArgumentException("Please Enter Valid Inputs");
        }
        userDTO.setPass(passwordEncoder.encode(userDTO.getPass()));
        UserEntity user = userConverter.convertUserDTOToEntity(userDTO);
        user = userRepository.save(user);
        userDTO = userConverter.convertUserEntityToDTO(user);
        return userDTO;
    }

    @Override
    public void deleteUser(long userId) {
        boolean isUserExit = userRepository.existsById(userId);
        if (!isUserExit) {
            throw new UserNotFound("User Does Not Exit");
        }
        userRepository.deleteById(userId);
    }

    @Override
    public UserDTO getMyUser(long userId) {
        UserDTO userDTO = new UserDTO();
        boolean isUserExit = userRepository.existsById(userId);
        if (!isUserExit) {
            throw new UserNotFound("User Does Not Exit");
        }
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        if (userEntity.isPresent()) {
            UserEntity user = userEntity.get();
            userDTO = userConverter.convertUserEntityToDTO(user);
        }
        return userDTO;
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, long userId) {
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        if (userEntity.isPresent()) {
            UserEntity user = userEntity.get();
            user.setName(userDTO.getName());
            user.setPhoneNumber(userDTO.getPhoneNumber());
            UserDTO dto = userConverter.convertUserEntityToDTO(user);
            userRepository.save(user);
            return dto;
        }
        throw new UserNotFound("No User With This Id");
    }

    @Override
    public TokenModel login(UserDTO userModel, HttpServletResponse response) {
        if (userModel.getName() == null  || userModel.getPass()== null) {
            throw new IllegalArgumentException("Please Enter Valid Inputs");
        }
        Authentication authentication = manager.authenticate(new UsernamePasswordAuthenticationToken(userModel.getName(), userModel.getPass()));
        if (!authentication.isAuthenticated()) {
            throw new IllegalArgumentException();
        }
        UserEntity entity = userRepository.findByName(userModel.getName());
        userModel = userConverter.convertUserEntityToDTO(entity);
        TokenModel token = new TokenModel();
        System.out.println(userModel);
        token.setToken(jwtService.generateToken(userModel));
        jwtService.addJwtToCookie(token.getToken(),response);
        return token;
    }

}

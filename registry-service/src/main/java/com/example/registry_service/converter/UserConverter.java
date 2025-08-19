package com.example.registry_service.converter;

import com.example.registry_service.dto.UserDTO;
import com.example.registry_service.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public UserEntity convertUserDTOToEntity(UserDTO userDTO) {
        UserEntity user = new UserEntity();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setPass(userDTO.getPass());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        return user;
    }

    public UserDTO convertUserEntityToDTO(UserEntity userEntity) {
        UserDTO dto = new UserDTO();
        dto.setId(userEntity.getId());
        dto.setEmail(userEntity.getEmail());
        dto.setName(userEntity.getName());
        dto.setPass(userEntity.getPass());
        dto.setPhoneNumber(userEntity.getPhoneNumber());
        return dto;
    }

}

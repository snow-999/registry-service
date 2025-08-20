package com.example.registry_service.service;

import com.example.registry_service.converter.UserConverter;
import com.example.registry_service.dto.MyUserPrincipal;
import com.example.registry_service.dto.UserDTO;
import com.example.registry_service.entity.UserEntity;
import com.example.registry_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    UserConverter userConverter;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(userName);
        UserDTO model = userConverter.convertUserEntityToDTO(user);
        if (user == null) {
            System.out.println("User Not Found");
            throw new UsernameNotFoundException("User Name Not Found");
        }
        return new MyUserPrincipal(model);
    }
}

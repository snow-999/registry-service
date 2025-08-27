package com.example.registry_service.controller;

import com.example.registry_service.dto.TokenModel;
import com.example.registry_service.dto.UserDTO;
import com.example.registry_service.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("signup")
    public ResponseEntity<UserDTO> signup(@RequestBody UserDTO userDTO) {
        userDTO = userService.signup(userDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @GetMapping("myuser/{id}")
    public ResponseEntity<UserDTO> getMyUser(@PathVariable long id) {
        UserDTO userDTO = userService.getMyUser(id);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping("login")
    public TokenModel greatMe (@RequestBody UserDTO userModel, HttpServletResponse response) {
        return userService.login(userModel, response);
    }

    // TODO edit user data except email
    @PutMapping("updateuser/{userId}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable long userId) {
        userDTO = userService.updateUser(userDTO, userId);
        return new ResponseEntity<>(userDTO, HttpStatus.ACCEPTED);
    }


    // TODO see list of users

}

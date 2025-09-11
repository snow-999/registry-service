package com.example.registry_service.controller;

import com.example.registry_service.dto.Roles;
import com.example.registry_service.dto.TokenModel;
import com.example.registry_service.dto.UserDTO;
import com.example.registry_service.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        UserDTO userDTO = userService.getMyUserById(id);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping("login")
    public TokenModel login(@RequestBody UserDTO userModel, HttpServletResponse response) {
        return userService.login(userModel, response);
    }

//    @GetMapping("getemail")
//    public UserDTO getEmail(@RequestBody String email) {
//        return userService.getMyUserById();
//    }


    // TODO edit user data except email
    @PutMapping("updateuser/{userId}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable long userId) {
        userDTO = userService.updateUser(userDTO, userId);
        return new ResponseEntity<>(userDTO, HttpStatus.ACCEPTED);
    }


    // TODO see list of users
    @GetMapping("getallusers")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> userDTOList = userService.getAllUsers();
        return new ResponseEntity<>(userDTOList, HttpStatus.OK);
    }

    // TODO delete user
    @DeleteMapping("deleteuser/{userId}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable long userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    // TODO add new admin
    @PutMapping("addnewadmin/{userId}")
    public ResponseEntity<UserDTO> addNewAdmin(@RequestParam Roles roleName, @PathVariable long userId) {
        UserDTO userDTO = userService.addNewAdmin(roleName, userId);
        return new ResponseEntity<>(userDTO, HttpStatus.ACCEPTED);
    }
}

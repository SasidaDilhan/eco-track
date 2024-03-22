package com.example.eco_track_backend.controller;

import com.example.eco_track_backend.exceptions.UserNotFonudException;
import com.example.eco_track_backend.model.User;
import com.example.eco_track_backend.repository.UserRepository;
import com.example.eco_track_backend.request.UserAuthRequestDTO;
import com.example.eco_track_backend.response.UserLoginResponseDTO;
import com.example.eco_track_backend.response.UserResponseDTO;
import com.example.eco_track_backend.security.JwtService;
import com.example.eco_track_backend.service.UserService;
import jakarta.annotation.security.RolesAllowed;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;


    //    @RolesAllowed("ROLE_ADMIN")
    @RolesAllowed("ADMIN")
    @GetMapping(value = "/admin",headers = "VERSION=V1")
    public String sayHiAdmin() {

        return "Hi Admin";
    }



    //    @RolesAllowed("ROLE_USER")
    @RolesAllowed("DRIVER")
    @GetMapping(value = "/driver",headers = "VERSION=V1")
    public String sayHiUser() {

        return "Hi Driver";
    }

    @RolesAllowed("ADMIN")
    @GetMapping(value = "/users",headers = "VERSION=V1")
    public ResponseEntity<List<UserResponseDTO>> allUsers()throws UserNotFonudException {
        List<UserResponseDTO> userResponseDTO = userService.getAllUsers();
        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }
}

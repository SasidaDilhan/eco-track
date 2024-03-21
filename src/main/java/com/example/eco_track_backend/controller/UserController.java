package com.example.eco_track_backend.controller;

import com.example.eco_track_backend.model.User;
import com.example.eco_track_backend.repository.UserRepository;
import com.example.eco_track_backend.request.UserAuthRequestDTO;
import com.example.eco_track_backend.response.UserLoginResponseDTO;
import com.example.eco_track_backend.security.JwtService;
import jakarta.annotation.security.RolesAllowed;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@AllArgsConstructor
public class UserController {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    @PostMapping("/authenticate")
    public UserLoginResponseDTO authenticate(@RequestBody UserAuthRequestDTO requestDTO) {
        System.out.println(" ====authenticate user " + requestDTO.getUsername());

        User userOne = userRepository.findUserByEmail(requestDTO.getUsername()).orElseThrow(
                () -> new EntityNotFoundException("User not available")
        );


        List<String> roles = new ArrayList<>();
        if (requestDTO.getUsername().equals(userOne.getEmail())) {
            roles.add("ROLE_"+userOne.getRole());
        }

        //authenticated user
        User user = new User();
        user.setUsername(requestDTO.getUsername());
        user.setPassword("test123");
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("username", user.getUsername());
        extraClaims.put("roles", roles);
        extraClaims.put("name", user.getName());

        String token = jwtService.generateToken(user, extraClaims);
        return UserLoginResponseDTO.builder()
                .token(token)
                .build();
    }


    //    @RolesAllowed("ROLE_ADMIN")
    @RolesAllowed("ADMIN")
    @GetMapping("/admin")
    public String sayHiAdmin() {

        return "Hi Admin";
    }

    //    @RolesAllowed("ROLE_USER")
    @RolesAllowed("DRIVER")
    @GetMapping("/driver")
    public String sayHiUser() {

        return "Hi Driver";
    }
}

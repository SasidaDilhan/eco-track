package com.example.eco_track_backend.controller;

import com.example.eco_track_backend.model.User;
import com.example.eco_track_backend.request.UserAuthRequestDTO;
import com.example.eco_track_backend.response.UserLoginResponseDTO;
import com.example.eco_track_backend.security.JwtService;
import jakarta.annotation.security.RolesAllowed;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
public class UserController {

    private final JwtService jwtService;

    @PostMapping("/authenticate")
    public UserLoginResponseDTO authenticate(@RequestBody UserAuthRequestDTO requestDTO) {
        System.out.println(" ====authenticate user " + requestDTO.getUsername());

        List<String> roles = new ArrayList<>();
        if (requestDTO.getUsername().equals("user1")) {
            roles.add("ROLE_USER");
        }
        if (requestDTO.getUsername().equals("user2")) {
            roles.addAll(List.of("ROLE_USER", "ROLE_ADMIN"));
        }

        //authenticated user
        User user = new User();
        user.setUsername(requestDTO.getUsername());
        user.setPassword(requestDTO.getPassword());
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
    @RolesAllowed("USER")
    @GetMapping("/user")
    public String sayHiUser() {

        return "Hi User";
    }
}

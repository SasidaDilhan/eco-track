package com.example.eco_track_backend.controller;

import com.example.eco_track_backend.exceptions.InvalidCredentialsException;
import com.example.eco_track_backend.model.User;
import com.example.eco_track_backend.repository.UserRepository;
import com.example.eco_track_backend.request.UserAuthRequestDTO;
import com.example.eco_track_backend.response.UserLoginResponseDTO;
import com.example.eco_track_backend.security.JwtService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
public class AuthController {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/authenticate")
    public UserLoginResponseDTO authenticate(@RequestBody UserAuthRequestDTO requestDTO)throws InvalidCredentialsException {
        System.out.println(" ====authenticate user " + requestDTO.getUsername());

        User userOne = userRepository.findUserByEmail(requestDTO.getUsername()).orElseThrow(
                () -> new EntityNotFoundException("User not available")
        );


        List<String> roles = new ArrayList<>();
        if (requestDTO.getUsername().equals(userOne.getEmail())) {
            roles.add("ROLE_"+userOne.getRole());
        }
String hashedPassword = passwordEncoder.encode(requestDTO.getPassword());
        //authenticated user
        User user = new User();
        user.setUsername(requestDTO.getUsername());
        user.setPassword(hashedPassword);


        // Check if the provided password matches the stored hashed password
        if (!passwordEncoder.matches(requestDTO.getPassword(), userOne.getPassword())) {
            throw new InvalidCredentialsException("Invalid credentials");
        }

        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("username", user.getUsername());
        extraClaims.put("roles", roles);
        extraClaims.put("password", user.getPassword());
        extraClaims.put("name", user.getName());


        String token = jwtService.generateToken(user, extraClaims);
        return UserLoginResponseDTO.builder()
                .token(token)
                .build();
    }
}

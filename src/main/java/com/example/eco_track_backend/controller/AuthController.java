package com.example.eco_track_backend.controller;

import com.example.eco_track_backend.exceptions.InvalidCredentialsException;
import com.example.eco_track_backend.exceptions.TruckDriverNotFoundException;
import com.example.eco_track_backend.exceptions.UserNotFonudException;
import com.example.eco_track_backend.model.TruckDriver;
import com.example.eco_track_backend.model.User;
import com.example.eco_track_backend.repository.TruckDriverRepository;
import com.example.eco_track_backend.repository.UserRepository;
import com.example.eco_track_backend.request.UserAuthRequestDTO;
import com.example.eco_track_backend.response.UserLoginResponseDTO;
import com.example.eco_track_backend.security.JwtService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@AllArgsConstructor
public class AuthController {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final TruckDriverRepository truckDriverRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping(value = "/authenticate",headers = "VERSION=V1")
    public UserLoginResponseDTO authenticate(@RequestBody UserAuthRequestDTO requestDTO) throws InvalidCredentialsException, UserNotFonudException, TruckDriverNotFoundException {
        System.out.println(" ====authenticate user " + requestDTO.getUsername());

        String email = requestDTO.getUsername();

        Optional<User> userOptional = userRepository.findUserByEmail(email);

        List<String> roles = new ArrayList<>();

        if (!userOptional.isPresent()){
            Optional<TruckDriver> truckDriverOptional = truckDriverRepository.findTruckDriverByEmail(email);
            if (!truckDriverOptional.isPresent()){
                throw new UserNotFonudException("No User Found!!");
            }
            else {
                TruckDriver truckDriverOne = truckDriverOptional.get();
                roles.add("ROLE_"+truckDriverOne.getRole());

                String hashedPassword = passwordEncoder.encode(requestDTO.getPassword());
                //authenticated user
                User user = new User();
                user.setUsername(requestDTO.getUsername());
                user.setPassword(hashedPassword);


                // Check if the provided password matches the stored hashed password
                if (!passwordEncoder.matches(requestDTO.getPassword(), truckDriverOne.getPassword())) {
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
        else {

            User userOne = userOptional.get();
            roles.add("ROLE_"+userOne.getRole());

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
}

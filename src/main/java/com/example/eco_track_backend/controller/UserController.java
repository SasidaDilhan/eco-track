package com.example.eco_track_backend.controller;

import com.example.eco_track_backend.model.User;
import com.example.eco_track_backend.repository.UserRepository;
import com.example.eco_track_backend.request.UserAuthRequestDTO;
import com.example.eco_track_backend.response.UserLoginResponseDTO;
import com.example.eco_track_backend.security.JwtService;
import jakarta.annotation.security.RolesAllowed;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //    @RolesAllowed("ROLE_ADMIN")
    @RolesAllowed("ADMIN")
    @GetMapping("/admin")
    public String sayHiAdmin() {

        return "Hi Admin";
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/admin/{id}/{password}")
    public void getAdminPassword(@PathVariable Long id,@PathVariable String password) {

        Optional<User> userOpt = userRepository.findById(id);
        String encodedPassword = passwordEncoder.encode(password);

        if (userOpt.isPresent()){
            User user = userOpt.get();
            String userPasword = user.getPassword();
            System.out.println(user.getPassword());
            System.out.println(encodedPassword);
            if (passwordEncoder.matches(userPasword, encodedPassword)){
                System.out.println("pasword match");
            }else {
                System.out.println("not match");
            }
        }

    }

    //    @RolesAllowed("ROLE_USER")
    @RolesAllowed("DRIVER")
    @GetMapping("/driver")
    public String sayHiUser() {

        return "Hi Driver";
    }


}

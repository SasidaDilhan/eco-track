package com.example.eco_track_backend.controller;

import com.example.eco_track_backend.model.User;
import com.example.eco_track_backend.request.UserRegisterRequestDTO;
import com.example.eco_track_backend.service.UserRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RegisterController {
    private final UserRegisterService userRegisterService;

    @PostMapping(value = "/register", headers = "VERSION=V1")
    public ResponseEntity<ResponseEntity<User>> createUser(@RequestBody UserRegisterRequestDTO userRegisterRequestDTO) {

        return ResponseEntity.ok(userRegisterService.saveUser(userRegisterRequestDTO));
    }
}

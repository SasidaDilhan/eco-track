package com.example.eco_track_backend.service;

import com.example.eco_track_backend.model.User;
import com.example.eco_track_backend.request.UserRegisterRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserRegisterService {

    ResponseEntity<User> saveUser(UserRegisterRequestDTO userRegisterRequestDTO);
}

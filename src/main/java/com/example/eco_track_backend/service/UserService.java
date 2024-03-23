package com.example.eco_track_backend.service;

import com.example.eco_track_backend.exceptions.UserNotFonudException;
import com.example.eco_track_backend.model.User;
import com.example.eco_track_backend.response.UserResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UserResponseDTO> getAllUsers() throws UserNotFonudException;

    List<UserResponseDTO> getUserByEmail(String email);
}

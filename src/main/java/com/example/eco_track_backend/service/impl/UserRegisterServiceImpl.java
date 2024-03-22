package com.example.eco_track_backend.service.impl;

import com.example.eco_track_backend.model.User;
import com.example.eco_track_backend.repository.UserRepository;
import com.example.eco_track_backend.request.UserRegisterRequestDTO;
import com.example.eco_track_backend.service.UserRegisterService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserRegisterServiceImpl implements UserRegisterService {

private final UserRepository userRepository;
private final ModelMapper modelMapper;
private final PasswordEncoder passwordEncoder;
    @Override
    public ResponseEntity<User> saveUser(UserRegisterRequestDTO userRegisterRequestDTO) {
        User user = modelMapper.map(userRegisterRequestDTO, User.class);
        user.setPassword(passwordEncoder.encode(userRegisterRequestDTO.getPassword()));
        userRepository.save(user);
        return new  ResponseEntity<>(user, HttpStatus.CREATED);
    }
}

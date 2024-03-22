package com.example.eco_track_backend.service.impl;

import com.example.eco_track_backend.model.User;
import com.example.eco_track_backend.repository.UserRepository;
import com.example.eco_track_backend.response.UserResponseDTO;
import com.example.eco_track_backend.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<UserResponseDTO> getAllUsers() {

        List<User> userList = userRepository.findAll();

        return userList.stream()
                .map(users -> modelMapper.map(users, UserResponseDTO.class))
                .collect(Collectors.toList());
    }
}

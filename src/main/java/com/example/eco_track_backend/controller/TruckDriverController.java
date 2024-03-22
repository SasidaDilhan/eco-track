package com.example.eco_track_backend.controller;


import com.example.eco_track_backend.model.User;
import com.example.eco_track_backend.repository.UserRepository;
import com.example.eco_track_backend.request.TruckDriverRequestDTO;
import com.example.eco_track_backend.service.TruckDriverService;
import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class TruckDriverController {

    private final TruckDriverService truckDriverService;
    private UserRepository userRepository;


    @RolesAllowed("ADMIN")
    @PostMapping("/truckdriver")
    public ResponseEntity<TruckDriverRequestDTO> create(@RequestBody TruckDriverRequestDTO truckDriverRequestDTO) {

        return truckDriverService.save(truckDriverRequestDTO);
    }

    @GetMapping("/user")
    public List<User> get(){
        List<User> userList = userRepository.findAll();
        return userList;
    }
}

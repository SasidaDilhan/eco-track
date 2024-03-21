package com.example.eco_track_backend.controller;


import com.example.eco_track_backend.model.TruckDriver;
import com.example.eco_track_backend.request.TruckDriverRequestDTO;
import com.example.eco_track_backend.service.TruckDriverService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TruckDriverRegisterController {

    private final TruckDriverService truckDriverService;


    @PostMapping("/truckdriver")
    public ResponseEntity<TruckDriverRequestDTO> create(@RequestBody TruckDriverRequestDTO truckDriverRequestDTO) {

        return truckDriverService.save(truckDriverRequestDTO);
    }
}

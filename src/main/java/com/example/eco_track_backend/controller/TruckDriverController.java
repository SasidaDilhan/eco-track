package com.example.eco_track_backend.controller;


import com.example.eco_track_backend.exceptions.TruckDriverNotFoundException;
import com.example.eco_track_backend.model.TruckDriver;
import com.example.eco_track_backend.model.User;
import com.example.eco_track_backend.repository.TruckDriverRepository;
import com.example.eco_track_backend.repository.UserRepository;
import com.example.eco_track_backend.request.TruckDriverRequestDTO;
import com.example.eco_track_backend.response.RouteResponseDTO;
import com.example.eco_track_backend.service.TruckDriverService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class TruckDriverController {

    private final TruckDriverService truckDriverService;
    private final TruckDriverRepository truckDriverRepository;
    private UserRepository userRepository;


    @RolesAllowed("ADMIN")
    @PostMapping(value = "/truckdriver", headers = "VERSION=V1")
    public ResponseEntity<String> create(@RequestBody @Valid TruckDriverRequestDTO truckDriverRequestDTO) throws TruckDriverNotFoundException {
        truckDriverService.save(truckDriverRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Truck Driver Created!");
    }

    @RolesAllowed("ADMIN")
    @GetMapping(value = "/truckdriver", headers = "VERSION=V1")
    public List<TruckDriver> get() {
        return truckDriverRepository.findAll();
    }

    @RolesAllowed("ADMIN")
    @GetMapping(value = "/truckdriver/{driver_id}", headers = "VERSION=V1")
    public ResponseEntity<List<RouteResponseDTO>> getRoutesByDriver(@PathVariable("driver_id")@Valid Long id) {
        List<RouteResponseDTO> routeResponseDTOList = truckDriverService.findByDriver(id);

        return new ResponseEntity<>(routeResponseDTOList, HttpStatus.OK);
    }
}

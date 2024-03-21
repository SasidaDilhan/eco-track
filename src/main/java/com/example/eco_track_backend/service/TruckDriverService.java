package com.example.eco_track_backend.service;

import com.example.eco_track_backend.request.TruckDriverRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface TruckDriverService {
    ResponseEntity<TruckDriverRequestDTO> save(TruckDriverRequestDTO truckDriverRequestDTO);
}

package com.example.eco_track_backend.service;

import com.example.eco_track_backend.exceptions.TruckDriverNotFoundException;
import com.example.eco_track_backend.request.TruckDriverRequestDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface TruckDriverService {
    void save(TruckDriverRequestDTO truckDriverRequestDTO) throws TruckDriverNotFoundException;
}

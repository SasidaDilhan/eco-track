package com.example.eco_track_backend.service;

import com.example.eco_track_backend.exceptions.TruckDriverNotFoundException;
import com.example.eco_track_backend.request.TruckDriverRequestDTO;
import com.example.eco_track_backend.response.RouteResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TruckDriverService {
    void save(TruckDriverRequestDTO truckDriverRequestDTO) throws TruckDriverNotFoundException;


    List<RouteResponseDTO> findByDriver(Long id);
}

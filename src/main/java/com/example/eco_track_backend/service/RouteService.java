package com.example.eco_track_backend.service;

import com.example.eco_track_backend.exceptions.RouteNotFoundException;
import com.example.eco_track_backend.exceptions.TruckDriverNotFoundException;
import com.example.eco_track_backend.request.RouteRequestDTO;
import com.example.eco_track_backend.response.RouteResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RouteService {
    void createRoute(RouteRequestDTO routeRequestDTO) throws TruckDriverNotFoundException;

    List<RouteResponseDTO> getAllRoutes( )throws RouteNotFoundException;
}

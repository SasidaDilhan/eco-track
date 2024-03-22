package com.example.eco_track_backend.controller;


import com.example.eco_track_backend.exceptions.RouteNotFoundException;
import com.example.eco_track_backend.exceptions.TruckDriverNotFoundException;
import com.example.eco_track_backend.request.RouteRequestDTO;
import com.example.eco_track_backend.response.RouteResponseDTO;
import com.example.eco_track_backend.service.RouteService;
import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class RouteController {

    private final RouteService routeService;

    @RolesAllowed("ADMIN")
    @PostMapping(value = "/route/{route_id}", headers = "VERSION=V1")
    public ResponseEntity<String> addRoute(@RequestBody RouteRequestDTO routeRequestDTO, @PathVariable("route_id") Long id) throws TruckDriverNotFoundException {

        routeService.createRoute(routeRequestDTO, id);
        return ResponseEntity.status(HttpStatus.CREATED).body("Route Created!");
    }

    @RolesAllowed("ADMIN")
    @GetMapping(value = "/route", headers = "VERSION=V1")
    public ResponseEntity<List<RouteResponseDTO>> getAll() throws RouteNotFoundException {

        List<RouteResponseDTO> routeResponseList = routeService.getAllRoutes();
        return new ResponseEntity<>(routeResponseList, HttpStatus.OK);

    }

}

package com.example.eco_track_backend.controller;

import com.example.eco_track_backend.exceptions.RouteNotFoundException;
import com.example.eco_track_backend.exceptions.UserNotFonudException;
import com.example.eco_track_backend.model.DisposalPlaces;
import com.example.eco_track_backend.request.DisposalPlacesRequestDTO;
import com.example.eco_track_backend.response.DisposalPlaceResponseDTO;
import com.example.eco_track_backend.service.DisposalPlaceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class DisposalPlaceController {

    private final DisposalPlaceService disposalPlaceService;

    @PostMapping(value = "/routes/{route_id}/disposalPlaces", headers = "VERSION=V1")
    public ResponseEntity<ResponseEntity<DisposalPlaces>> addDisposalPlace (@RequestBody DisposalPlacesRequestDTO disposalPlacesRequestDTO, @PathVariable("route_id")Long routeId)throws RouteNotFoundException {

        return ResponseEntity.ok(disposalPlaceService.addDisposalPlace(disposalPlacesRequestDTO,routeId));
    }
}

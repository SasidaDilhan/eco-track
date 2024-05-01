package com.example.eco_track_backend.service;


import com.example.eco_track_backend.exceptions.RouteNotFoundException;
import com.example.eco_track_backend.model.DisposalPlaces;
import com.example.eco_track_backend.request.DisposalPlacesRequestDTO;
import com.example.eco_track_backend.response.DisposalPlaceResponseDTO;
import org.springframework.http.ResponseEntity;

public interface DisposalPlaceService {

    DisposalPlaceResponseDTO addDisposalPlace(DisposalPlacesRequestDTO disposalPlacesRequestDTO, Long routeId)throws RouteNotFoundException;
}

package com.example.eco_track_backend.service;


import com.example.eco_track_backend.exceptions.RouteNotFoundException;
import com.example.eco_track_backend.model.DisposalPlaces;
import com.example.eco_track_backend.request.DisposalPlacesRequestDTO;
import org.springframework.http.ResponseEntity;

public interface DisposalPlaceService {

    ResponseEntity<DisposalPlaces> addDisposalPlace(DisposalPlacesRequestDTO disposalPlacesRequestDTO,Long routeId)throws RouteNotFoundException;
}

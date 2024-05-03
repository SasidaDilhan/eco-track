package com.example.eco_track_backend.controller;

import com.example.eco_track_backend.exceptions.RouteNotFoundException;
import com.example.eco_track_backend.model.DisposalPlaces;
import com.example.eco_track_backend.request.DisposalPlacesRequestDTO;
import com.example.eco_track_backend.response.DisposalPlaceResponseDTO;
import com.example.eco_track_backend.service.DisposalPlaceService;
import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class DisposalPlaceController {

    private final DisposalPlaceService disposalPlaceService;

@RolesAllowed("ADMIN")
    @PostMapping(value = "/routes/{route_id}/disposalPlaces", headers = "VERSION=V1")
    public DisposalPlaceResponseDTO addDisposalPlace (@RequestBody DisposalPlacesRequestDTO disposalPlacesRequestDTO, @PathVariable("route_id")Long routeId)throws RouteNotFoundException {


        System.out.println("longitude :"+disposalPlacesRequestDTO.getLongitude());
        return disposalPlaceService.addDisposalPlace(disposalPlacesRequestDTO,routeId);

    }


//    @GetMapping("/routes/{route_id}/disposalPlaces")
//    public DisposalPlaceResponseDTO getAllDisposalPlace(@RequestBody){
//
//
//    }

//    @GetMapping("/routes/{route_id}/disposalPlaces")
//    public DisposalPlaceResponseDTO getAllDisposalPlace(@PathVariable("disposal_place")Long disposalPlaceId){
//
////        return disposalPlaceService.getAllDisposalPlace()
//    }
}

package com.example.eco_track_backend.controller;

import com.example.eco_track_backend.exceptions.DisposalPlaceNotFoundException;
import com.example.eco_track_backend.exceptions.RouteNotFoundException;
import com.example.eco_track_backend.model.DisposalPlaces;
import com.example.eco_track_backend.request.DisposalPlacesRequestDTO;
import com.example.eco_track_backend.response.DisposalPlaceResponseDTO;
import com.example.eco_track_backend.service.DisposalPlaceService;
import jakarta.annotation.security.RolesAllowed;
import jdk.dynalink.linker.LinkerServices;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/routes/disposalPlaces")
    public List<DisposalPlaceResponseDTO> getAllDisposalPlace(){

        return disposalPlaceService.getAllDisposalPlace();
    }


    @GetMapping("/routes/{route_id}/disposalPlaces/{disposal_place_id}")
    public DisposalPlaceResponseDTO getSpecificRouteSpecificDisposalPlace(@PathVariable("disposal_place_id")Long disposalPlaceId,@PathVariable("route_id")Long routeId)throws DisposalPlaceNotFoundException,RouteNotFoundException {

    return disposalPlaceService.getSpecificRouteSpecificDisposalPlace(disposalPlaceId,routeId);

    }

    @GetMapping("/routes/{route_id}/disposalPlaces")
    public List<DisposalPlaceResponseDTO> getSpecificRouteDisposalPlaces(@PathVariable("route_id")Long routeId)throws DisposalPlaceNotFoundException,RouteNotFoundException {

        return disposalPlaceService.getSpecificRouteDisposalPlaces(routeId);

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

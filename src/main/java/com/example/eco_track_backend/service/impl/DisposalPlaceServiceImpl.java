package com.example.eco_track_backend.service.impl;

import com.example.eco_track_backend.exceptions.RouteNotFoundException;
import com.example.eco_track_backend.exceptions.UserNotFonudException;
import com.example.eco_track_backend.model.DisposalPlaces;
import com.example.eco_track_backend.model.Route;
import com.example.eco_track_backend.model.User;
import com.example.eco_track_backend.repository.DisposalPlaceRepository;
import com.example.eco_track_backend.repository.RouteRepository;
import com.example.eco_track_backend.repository.UserRepository;
import com.example.eco_track_backend.request.DisposalPlacesRequestDTO;
import com.example.eco_track_backend.response.DisposalPlaceResponseDTO;
import com.example.eco_track_backend.service.DisposalPlaceService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DisposalPlaceServiceImpl implements DisposalPlaceService {

    private final DisposalPlaceRepository disposalPlaceRepository;
    private final UserRepository userRepository;
    private final RouteRepository routeRepository;
    private final ModelMapper modelMapper;



    public DisposalPlaceResponseDTO addDisposalPlace(DisposalPlacesRequestDTO disposalPlacesRequestDTO,Long routeId)throws RouteNotFoundException {

        Route route = routeRepository.findById(routeId).orElseThrow(
                ()-> new RouteNotFoundException("that route not in a database")
        );

        DisposalPlaces disposalPlaces = modelMapper.map(disposalPlacesRequestDTO,DisposalPlaces.class);

        disposalPlaces.setRoute(route);

        disposalPlaceRepository.save(disposalPlaces);

       return DisposalPlaceResponseDTO.builder()
                .id(disposalPlaces.getId())
                .latitude(disposalPlaces.getLatitude())
                .longitude(disposalPlaces.getLongitude())
                .name(disposalPlaces.getName())
               .route(disposalPlaces.getRoute().getId()).build();




    }

    @Override
    public List<DisposalPlaceResponseDTO> getAllDisposalPlace() {

        List<DisposalPlaces> disposalPlacesList =disposalPlaceRepository.findAll();

        return disposalPlacesList.stream().map(disposalPlaces -> DisposalPlaceResponseDTO.builder().id(disposalPlaces.getId()).name(disposalPlaces.getName()).longitude(disposalPlaces.getLongitude()).latitude(disposalPlaces.getLatitude()).route(disposalPlaces.getRoute().getId()).build()).toList();
    }


}

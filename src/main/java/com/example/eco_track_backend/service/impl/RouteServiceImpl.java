package com.example.eco_track_backend.service.impl;

import com.example.eco_track_backend.exceptions.RouteNotFoundException;
import com.example.eco_track_backend.exceptions.TruckDriverNotFoundException;
import com.example.eco_track_backend.model.Route;
import com.example.eco_track_backend.model.TruckDriver;
import com.example.eco_track_backend.repository.RouteRepository;
import com.example.eco_track_backend.repository.TruckDriverRepository;
import com.example.eco_track_backend.request.RouteRequestDTO;
import com.example.eco_track_backend.response.RouteResponseDTO;
import com.example.eco_track_backend.service.RouteService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final TruckDriverRepository truckDriverRepository;
    private final ModelMapper modelMapper;

    @Override
    public void createRoute(RouteRequestDTO routeRequestDTO, Long id) throws TruckDriverNotFoundException {

        TruckDriver truckDriver = truckDriverRepository.findById(id).orElseThrow(
                () -> new TruckDriverNotFoundException("Truck Driver Not Found!")
        );

        Route route = modelMapper.map(routeRequestDTO, Route.class);
        route.setTruckDriver(truckDriver);
        routeRepository.save(route);
    }

    @Override
    public List<RouteResponseDTO> getAllRoutes() throws RouteNotFoundException {
        List<Route> routeList = routeRepository.findAll();

        if (routeList.isEmpty()) {
            throw new RouteNotFoundException("Route Not Found!");
        }

        return routeList.stream()
                .map(routes -> modelMapper.map(routes, RouteResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<Route> createRouteFirst(RouteRequestDTO routeRequestDTO) {

        Route route = modelMapper.map(routeRequestDTO,Route.class);

        routeRepository.save(route);

        return new ResponseEntity<>(route, HttpStatus.CREATED);
    }

    @Override
    public RouteResponseDTO deleteSpecificRoute(Long routeId) throws RouteNotFoundException {

        Route route = routeRepository.findById(routeId).orElseThrow(
                ()-> new RouteNotFoundException("that route not in a database")
        );

        routeRepository.deleteById(route.getId());

        RouteResponseDTO routeResponseDTO = new RouteResponseDTO();

        routeResponseDTO.setLatitude(route.getLatitude());
        routeResponseDTO.setLongitude(route.getLongitude());
        routeResponseDTO.setName(route.getName());
        routeResponseDTO.setId(route.getId());

        return routeResponseDTO;
    }

    @Override
    public RouteResponseDTO getSpecificRoute(Long routeId)throws RouteNotFoundException {

        Route route = routeRepository.findById(routeId).orElseThrow(
                ()-> new RouteNotFoundException("That route not in a Database")
        );



        RouteResponseDTO routeResponseDTO = new RouteResponseDTO();

        routeResponseDTO.setLatitude(route.getLatitude());
        routeResponseDTO.setLongitude(route.getLongitude());
        routeResponseDTO.setName(route.getName());
        routeResponseDTO.setId(route.getId());

        return routeResponseDTO;

    }

}

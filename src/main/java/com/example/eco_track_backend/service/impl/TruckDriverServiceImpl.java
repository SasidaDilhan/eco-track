package com.example.eco_track_backend.service.impl;

import com.example.eco_track_backend.exceptions.TruckDriverNotFoundException;
import com.example.eco_track_backend.model.Route;
import com.example.eco_track_backend.model.TruckDriver;
import com.example.eco_track_backend.repository.TruckDriverRepository;
import com.example.eco_track_backend.request.TruckDriverRequestDTO;
import com.example.eco_track_backend.response.RouteResponseDTO;
import com.example.eco_track_backend.service.TruckDriverService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TruckDriverServiceImpl implements TruckDriverService {

    private final TruckDriverRepository truckDriverRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Override
    public void save(TruckDriverRequestDTO truckDriverRequestDTO) throws TruckDriverNotFoundException {

        Optional<TruckDriver> truckDriverOptional = truckDriverRepository.findTruckDriverByEmail(truckDriverRequestDTO.getEmail());

        if (truckDriverOptional.isPresent()){
            throw new TruckDriverNotFoundException("Already exist!");
        }

        TruckDriver truckDriver = modelMapper.map(truckDriverRequestDTO, TruckDriver.class);
        truckDriver.setPassword(passwordEncoder.encode(truckDriverRequestDTO.getPassword()));
        truckDriverRepository.save(truckDriver);

    }

    @Override
    public List<RouteResponseDTO> findByDriver(Long id) {
        Optional<TruckDriver> truckDriverOptional = truckDriverRepository.findById(id);

        if (truckDriverOptional.isPresent()){
            TruckDriver truckDriver = truckDriverOptional.get();
            List<Route> routeList = truckDriver.getRouteList();

            return routeList.stream()
                    .map(route -> modelMapper.map(route, RouteResponseDTO.class))
                    .collect(Collectors.toList());

        }
        return null;
    }
}

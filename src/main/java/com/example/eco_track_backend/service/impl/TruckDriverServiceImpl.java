package com.example.eco_track_backend.service.impl;

import com.example.eco_track_backend.exceptions.TruckDriverNotFoundException;
import com.example.eco_track_backend.model.TruckDriver;
import com.example.eco_track_backend.repository.TruckDriverRepository;
import com.example.eco_track_backend.request.TruckDriverRequestDTO;
import com.example.eco_track_backend.service.TruckDriverService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
}

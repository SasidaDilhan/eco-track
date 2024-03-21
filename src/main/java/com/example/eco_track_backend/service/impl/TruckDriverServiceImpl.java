package com.example.eco_track_backend.service.impl;

import com.example.eco_track_backend.model.TruckDriver;
import com.example.eco_track_backend.repository.TruckDriverRepository;
import com.example.eco_track_backend.request.TruckDriverRequestDTO;
import com.example.eco_track_backend.service.TruckDriverService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TruckDriverServiceImpl implements TruckDriverService {

    private final TruckDriverRepository truckDriverRepository;
    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<TruckDriverRequestDTO> save(TruckDriverRequestDTO truckDriverRequestDTO) {
        TruckDriver truckDriver = modelMapper.map(truckDriverRequestDTO, TruckDriver.class);
        truckDriverRepository.save(truckDriver);
        return ResponseEntity.ok(truckDriverRequestDTO);

    }
}

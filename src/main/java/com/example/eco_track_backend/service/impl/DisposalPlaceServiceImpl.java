package com.example.eco_track_backend.service.impl;

import com.example.eco_track_backend.exceptions.UserNotFonudException;
import com.example.eco_track_backend.model.DisposalPlaces;
import com.example.eco_track_backend.model.User;
import com.example.eco_track_backend.repository.DisposalPlaceRepository;
import com.example.eco_track_backend.repository.UserRepository;
import com.example.eco_track_backend.request.DisposalPlacesRequestDTO;
import com.example.eco_track_backend.response.DisposalPlaceResponseDTO;
import com.example.eco_track_backend.service.DisposalPlaceService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DisposalPlaceServiceImpl implements DisposalPlaceService {

    private final DisposalPlaceRepository disposalPlaceRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    @Override
    public DisposalPlaceResponseDTO addDisposalPlace(DisposalPlacesRequestDTO disposalPlacesRequestDTO, String email) throws UserNotFonudException {

        User user = userRepository.findUserByEmail(email).orElseThrow(
                ()-> new UserNotFonudException("that email not found")
        );

        DisposalPlaces disposalPlaces = modelMapper.map(disposalPlacesRequestDTO,DisposalPlaces.class);

        return null;
    }
}

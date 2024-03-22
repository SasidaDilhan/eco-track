package com.example.eco_track_backend.service;

import com.example.eco_track_backend.exceptions.UserNotFonudException;
import com.example.eco_track_backend.request.DisposalPlacesRequestDTO;
import com.example.eco_track_backend.response.DisposalPlaceResponseDTO;

public interface DisposalPlaceService {
    DisposalPlaceResponseDTO addDisposalPlace(DisposalPlacesRequestDTO disposalPlacesRequestDTO, String email)throws UserNotFonudException;
}

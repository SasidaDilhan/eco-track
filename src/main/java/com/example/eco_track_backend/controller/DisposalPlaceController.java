package com.example.eco_track_backend.controller;

import com.example.eco_track_backend.exceptions.UserNotFonudException;
import com.example.eco_track_backend.request.DisposalPlacesRequestDTO;
import com.example.eco_track_backend.response.DisposalPlaceResponseDTO;
import com.example.eco_track_backend.service.DisposalPlaceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class DisposalPlaceController {

    private final DisposalPlaceService disposalPlaceService;


    public ResponseEntity<DisposalPlaceResponseDTO> addDisposalPlace(@RequestBody DisposalPlacesRequestDTO disposalPlacesRequestDTO, Authentication authentication) throws UserNotFonudException {

        User user = (User) authentication.getPrincipal();

        String email = user.getUsername();

        DisposalPlaceResponseDTO disposalPlaceResponseDTO = disposalPlaceService.addDisposalPlace(disposalPlacesRequestDTO,email);

        return new ResponseEntity<>(disposalPlaceResponseDTO, HttpStatus.OK);
    }
}

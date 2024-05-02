package com.example.eco_track_backend.service;

import com.example.eco_track_backend.exceptions.StoreItemNotFoundException;
import com.example.eco_track_backend.exceptions.UserNotFonudException;
import com.example.eco_track_backend.model.StoreItem;
import com.example.eco_track_backend.request.StoreItemRequestDTO;
import com.example.eco_track_backend.response.StoreItemResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StoreItemService {
    ResponseEntity<StoreItem> addStoreItem(StoreItemRequestDTO storeItemRequestDTO, String email)throws StoreItemNotFoundException, UserNotFonudException;

    List<StoreItemResponseDTO> getAllItems()throws StoreItemNotFoundException;

    List<StoreItemResponseDTO> getSpecificUserItems(Long userId)throws UserNotFonudException,StoreItemNotFoundException;

    StoreItemResponseDTO getSpecificUserSpecificItems(Long userId, Long storeItemId)throws UserNotFonudException,StoreItemNotFoundException;

    StoreItemResponseDTO deleteSpecificUserSpecificItems(Long userId, Long storeItemId)throws UserNotFonudException,StoreItemNotFoundException;
}

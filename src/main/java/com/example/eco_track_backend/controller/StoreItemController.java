package com.example.eco_track_backend.controller;

import com.example.eco_track_backend.exceptions.StoreItemNotFoundException;
import com.example.eco_track_backend.exceptions.UserNotFonudException;
import com.example.eco_track_backend.model.StoreItem;
import com.example.eco_track_backend.request.StoreItemRequestDTO;
import com.example.eco_track_backend.response.StoreItemResponseDTO;
import com.example.eco_track_backend.service.StoreItemService;
import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import org.apache.catalina.Store;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class StoreItemController {

    private  StoreItemService storeItemService;

//    @RolesAllowed("ADMIN")
    @PostMapping(value = "/users/store_items",headers = "VERSION=V1")
    public ResponseEntity<String> addStoreItem(@RequestBody StoreItemRequestDTO storeItemRequestDTO, Authentication authentication) throws StoreItemNotFoundException, UserNotFonudException {

        User user = (User) authentication.getPrincipal();

        String email = user.getUsername();

        System.out.println("email  : "+email);

        storeItemService.addStoreItem(storeItemRequestDTO,email);

        return ResponseEntity.status(HttpStatus.CREATED).body("item stored");

    }
    @RolesAllowed("ADMIN")
    @GetMapping(value = "/users/store_items",headers = "VERSION=V1")
    public List<StoreItemResponseDTO> getAllItem()throws StoreItemNotFoundException{

        return storeItemService.getAllItems();
    }



    @GetMapping(value = "/users/{user_id}/store_items",headers = "VERSION=V1")
    public List<StoreItemResponseDTO> getSpecificUserItems(@PathVariable("user_id")Long userId) throws UserNotFonudException, StoreItemNotFoundException {

      return storeItemService.getSpecificUserItems(userId);

    }


}

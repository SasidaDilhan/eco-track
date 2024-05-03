package com.example.eco_track_backend.controller;

import com.example.eco_track_backend.exceptions.StoreItemNotFoundException;
import com.example.eco_track_backend.exceptions.UserNotFonudException;
import com.example.eco_track_backend.request.StoreItemRequestDTO;
import com.example.eco_track_backend.response.StoreItemResponseDTO;
import com.example.eco_track_backend.service.StoreItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
public class StoreItemController {

    private  StoreItemService storeItemService;

//    @RolesAllowed("ADMIN")
    @PostMapping("/users/store_items")
    public ResponseEntity<String> addStoreItem(@ModelAttribute StoreItemRequestDTO storeItemRequestDTO, @RequestParam("imagePath") MultipartFile file, Authentication authentication) throws StoreItemNotFoundException, UserNotFonudException, IOException {

        User user = (User) authentication.getPrincipal();

        String email = user.getUsername();

        System.out.println("email  : "+email);

        storeItemService.addStoreItem(storeItemRequestDTO,email,file);

        return ResponseEntity.status(HttpStatus.CREATED).body("item stored");

    }
//    @RolesAllowed("ADMIN")
    @GetMapping("/users/store_items")
    public List<StoreItemResponseDTO> getAllItem()throws StoreItemNotFoundException{

        return storeItemService.getAllItems();
    }



    @GetMapping("/users/{user_id}/store_items")
    public List<StoreItemResponseDTO> getSpecificUserItems(@PathVariable("user_id")Long userId) throws UserNotFonudException, StoreItemNotFoundException {

      return storeItemService.getSpecificUserItems(userId);

    }

    @GetMapping("/users/{user_id}/store_items/{store_item_id}")
    public StoreItemResponseDTO getSpecificUserSpecificItems(@PathVariable("user_id")Long userId, @PathVariable("store_item_id")Long storeItemId)throws UserNotFonudException,StoreItemNotFoundException{

        return storeItemService.getSpecificUserSpecificItems(userId,storeItemId);
    }

    @DeleteMapping("/users/{user_id}/store_items/{store_item_id}")
    public StoreItemResponseDTO deleteSpecificUserSpecificItems(@PathVariable("user_id")Long userId, @PathVariable("store_item_id")Long storeItemId)throws UserNotFonudException,StoreItemNotFoundException{

        return storeItemService.deleteSpecificUserSpecificItems(userId,storeItemId);
    }

    @PutMapping("/users/{user_id}/store_items/{store_item_id}")
    public StoreItemResponseDTO updateSpecificUserSpecificItems(@PathVariable("user_id")Long userId, @PathVariable("store_item_id")Long storeItemId,@ModelAttribute StoreItemRequestDTO storeItemRequestDTO,@RequestParam("imagePath")MultipartFile file)throws UserNotFonudException,StoreItemNotFoundException,IOException{

        return storeItemService.updateSpecificUserSpecificItems(userId,storeItemId,storeItemRequestDTO,file);
    }


}

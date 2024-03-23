package com.example.eco_track_backend.service.impl;

import com.example.eco_track_backend.exceptions.StoreItemNotFoundException;
import com.example.eco_track_backend.exceptions.UserNotFonudException;
import com.example.eco_track_backend.model.StoreItem;
import com.example.eco_track_backend.model.User;
import com.example.eco_track_backend.repository.StoreItemRepository;
import com.example.eco_track_backend.repository.UserRepository;
import com.example.eco_track_backend.request.StoreItemRequestDTO;
import com.example.eco_track_backend.response.StoreItemResponseDTO;
import com.example.eco_track_backend.response.UserResponseDTO;
import com.example.eco_track_backend.service.StoreItemService;
import lombok.AllArgsConstructor;
import org.apache.catalina.Store;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StoreItemServiceImpl implements StoreItemService {

    private final StoreItemRepository storeItemRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;


    @Override
    public ResponseEntity<StoreItem> addStoreItem(StoreItemRequestDTO storeItemRequestDTO, String email) throws StoreItemNotFoundException, UserNotFonudException {

        User user = userRepository.findUserByEmail(email).orElseThrow(
                ()-> new UsernameNotFoundException("that user not in a database")
        );

        StoreItem storeItem = modelMapper.map(storeItemRequestDTO,StoreItem.class);

        storeItem.setUser(user);

        storeItemRepository.save(storeItem);

        return new ResponseEntity<>(storeItem,HttpStatus.CREATED);
    }

    @Override
    public List<StoreItemResponseDTO> getAllItems()throws StoreItemNotFoundException {

       List<StoreItem> storeItemList = storeItemRepository.findAll();

       if (storeItemList.isEmpty()) {
           throw new StoreItemNotFoundException("store items not in a database");
       };

//       return storeItemList.stream()
//               .map(storeItem -> modelMapper.map(storeItem,StoreItemResponseDTO.class)).collect(Collectors.toList());

        List<StoreItemResponseDTO> storeItemResponseDTOList = new ArrayList<>();

        for (StoreItem storeItem : storeItemList){

            StoreItemResponseDTO storeItemResponseDTO = new StoreItemResponseDTO();

            storeItemResponseDTO.setId(storeItem.getId());
            storeItemResponseDTO.setName(storeItem.getName());
            storeItemResponseDTO.setQuantity(storeItem.getQuantity());
            storeItemResponseDTO.setUser(storeItem.getUser().getId());

            storeItemResponseDTOList.add(storeItemResponseDTO);
        }

        return storeItemResponseDTOList;
    }
}

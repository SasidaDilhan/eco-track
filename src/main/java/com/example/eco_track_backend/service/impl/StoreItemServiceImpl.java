package com.example.eco_track_backend.service.impl;

import com.cloudinary.Cloudinary;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StoreItemServiceImpl implements StoreItemService {

    private final StoreItemRepository storeItemRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private Cloudinary cloudinary;


    @Override
    public ResponseEntity<StoreItem> addStoreItem(StoreItemRequestDTO storeItemRequestDTO, String email, MultipartFile file) throws StoreItemNotFoundException, UserNotFonudException, IOException {

        User user = userRepository.findUserByEmail(email).orElseThrow(
                ()-> new UsernameNotFoundException("that user not in a database")
        );

        StoreItem storeItem = modelMapper.map(storeItemRequestDTO,StoreItem.class);

        storeItem.setUser(user);

        // Upload file to Cloudinary
        Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), null);
        String imageUrl = (String) uploadResult.get("url");
        storeItem.setImagePath(imageUrl);

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
            storeItemResponseDTO.setImagePath(storeItem.getImagePath());

            storeItemResponseDTOList.add(storeItemResponseDTO);
        }

        return storeItemResponseDTOList;
    }

    public List<StoreItemResponseDTO> getSpecificUserItems(Long userId)throws UserNotFonudException,StoreItemNotFoundException{

        User user = userRepository.findById(userId).orElseThrow(
                ()-> new UserNotFonudException("That use not in a Database")
        );

        List<StoreItem> storeItemList = storeItemRepository.findStoreItemsByUserId(user.getId());

        if (storeItemList.isEmpty()) {
            throw new StoreItemNotFoundException("store items not in a database");
        };

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

    @Override
    public StoreItemResponseDTO getSpecificUserSpecificItems(Long userId, Long storeItemId) throws UserNotFonudException, StoreItemNotFoundException {

        User user = userRepository.findById(userId).orElseThrow(
                ()-> new UsernameNotFoundException("that user not in a data base")
        );

        List<StoreItem> storeItemList = user.getStoreItemList();

        StoreItem getToItem = storeItemList.stream().filter(storeItem -> storeItem.getId().equals(storeItemId)).findFirst().orElse(null);

        StoreItemResponseDTO storeItemResponseDTO = new StoreItemResponseDTO();

        if(getToItem == null){
            throw new StoreItemNotFoundException("store items not found");
        };
        storeItemResponseDTO.setId(getToItem.getId());
        storeItemResponseDTO.setDescription(getToItem.getDescription());
        storeItemResponseDTO.setQuantity(getToItem.getQuantity());
        storeItemResponseDTO.setName(getToItem.getName());
        storeItemResponseDTO.setImagePath(getToItem.getImagePath());
        storeItemResponseDTO.setUser(getToItem.getUser().getId());
        storeItemResponseDTO.setPrice(getToItem.getPrice());

        return storeItemResponseDTO;
    }

    @Override
    public StoreItemResponseDTO deleteSpecificUserSpecificItems(Long userId, Long storeItemId) throws UserNotFonudException, StoreItemNotFoundException {

        User user = userRepository.findById(userId).orElseThrow(
                ()-> new UsernameNotFoundException("that user not in a data base")
        );

        List<StoreItem> storeItemList = user.getStoreItemList();

        StoreItem deleteToItem = storeItemList.stream().filter(storeItem -> storeItem.getId().equals(storeItemId)).findFirst().orElse(null);

        if(deleteToItem == null){
            throw new StoreItemNotFoundException("store items not found");
        };
        storeItemRepository.delete(deleteToItem);

        StoreItemResponseDTO storeItemResponseDTO = new StoreItemResponseDTO();

        storeItemResponseDTO.setId(deleteToItem.getId());
        storeItemResponseDTO.setDescription(deleteToItem.getDescription());
        storeItemResponseDTO.setQuantity(deleteToItem.getQuantity());
        storeItemResponseDTO.setName(deleteToItem.getName());
        storeItemResponseDTO.setImagePath(deleteToItem.getImagePath());
        storeItemResponseDTO.setUser(deleteToItem.getUser().getId());

        return storeItemResponseDTO;

    }

    @Override
    public StoreItemResponseDTO updateSpecificUserSpecificItems(Long userId, Long storeItemId,StoreItemRequestDTO storeItemRequestDTO) throws UserNotFonudException, StoreItemNotFoundException {


        User user = userRepository.findById(userId).orElseThrow(
                ()-> new UsernameNotFoundException("that user not in a data base")
        );

        List<StoreItem> storeItemList = user.getStoreItemList();

        StoreItem updateToItem = storeItemList.stream().filter(storeItem -> storeItem.getId().equals(storeItemId)).findFirst().orElse(null);

        if(updateToItem == null){
            throw new StoreItemNotFoundException("store items not found");
        };

        updateToItem.setDescription(storeItemRequestDTO.getDescription());
        updateToItem.setImagePath(String.valueOf(storeItemRequestDTO.getImagePath()));
        updateToItem.setName(storeItemRequestDTO.getName());
        updateToItem.setQuantity(storeItemRequestDTO.getQuantity());
        updateToItem.setPrice(storeItemRequestDTO.getPrice());

        storeItemRepository.save(updateToItem);



        StoreItemResponseDTO storeItemResponseDTO = new StoreItemResponseDTO();

        storeItemResponseDTO.setId(updateToItem.getId());
        storeItemResponseDTO.setDescription(updateToItem.getDescription());
        storeItemResponseDTO.setQuantity(updateToItem.getQuantity());
        storeItemResponseDTO.setName(updateToItem.getName());
        storeItemResponseDTO.setImagePath(updateToItem.getImagePath());
        storeItemResponseDTO.setUser(updateToItem.getUser().getId());
        storeItemResponseDTO.setPrice(updateToItem.getPrice());

        return storeItemResponseDTO;


    }


}

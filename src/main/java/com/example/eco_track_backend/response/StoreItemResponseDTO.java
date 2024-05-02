package com.example.eco_track_backend.response;

import com.example.eco_track_backend.model.User;
import lombok.Data;

@Data
public class StoreItemResponseDTO {

    private Long id;
    private String name;
    private Long quantity;

    private String description;

    private String imagePath;

    private Long user;

}

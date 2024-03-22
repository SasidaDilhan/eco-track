package com.example.eco_track_backend.request;

import com.example.eco_track_backend.model.User;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class StoreItemRequestDTO {

    private Long id;
    private String name;
    private Long quantity;

}

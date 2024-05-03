package com.example.eco_track_backend.request;

import com.example.eco_track_backend.model.User;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class StoreItemRequestDTO {


    private String name;


    private Long quantity;


    private Double price;

    private String description;

    private MultipartFile imagePath;

}

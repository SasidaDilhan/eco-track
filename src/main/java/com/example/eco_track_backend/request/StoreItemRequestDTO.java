package com.example.eco_track_backend.request;

import com.example.eco_track_backend.model.User;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class StoreItemRequestDTO {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotNull(message = "Quantity cannot be null")
    @PositiveOrZero(message = "Quantity must be a positive number or zero")
    private Long quantity;

    @NotNull(message = "Quantity cannot be null")
    private Double price;

    private String description;

    private String imagePath;

}

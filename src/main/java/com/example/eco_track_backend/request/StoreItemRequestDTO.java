package com.example.eco_track_backend.request;

import com.example.eco_track_backend.model.User;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class StoreItemRequestDTO {

    @NotNull(message = "ID cannot be null")
    @PositiveOrZero(message = "ID must be a positive number or zero")
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotNull(message = "Quantity cannot be null")
    @PositiveOrZero(message = "Quantity must be a positive number or zero")
    private Long quantity;
}

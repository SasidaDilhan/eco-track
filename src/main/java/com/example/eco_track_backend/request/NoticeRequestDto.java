package com.example.eco_track_backend.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NoticeRequestDto {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Description cannot be blank")
    private String description;
}

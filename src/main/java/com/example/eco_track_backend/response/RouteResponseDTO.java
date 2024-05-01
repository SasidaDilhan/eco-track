package com.example.eco_track_backend.response;

import lombok.Data;

@Data
public class RouteResponseDTO {

    private Long id;
    private String name;
    private double latitude;
    private double longitude;
}

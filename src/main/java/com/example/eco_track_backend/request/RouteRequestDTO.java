package com.example.eco_track_backend.request;

import lombok.Data;

@Data
public class RouteRequestDTO {

    private String name;
    private double latitude;
    private double longitude;
}
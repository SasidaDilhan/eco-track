package com.example.eco_track_backend.request;

import com.example.eco_track_backend.model.Route;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class DisposalPlacesRequestDTO {

    private Long id;
    private String name;
    private Double latitude;
    private Double longitude;

    private Route route;
}

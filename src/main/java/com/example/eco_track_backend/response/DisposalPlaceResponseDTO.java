package com.example.eco_track_backend.response;

import com.example.eco_track_backend.model.Route;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DisposalPlaceResponseDTO {

    private Long id;
    private String name;
    private Double latitude;
    private Double longitude;


    private Long route;
}

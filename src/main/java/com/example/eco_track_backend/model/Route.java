package com.example.eco_track_backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "routes")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double latitude;
    private Double longitude;
    private String name;

    @OneToMany(mappedBy = "route")
    private List<User> userList = new ArrayList<>();

    @ManyToOne
    private TruckDriver truckDriver;

    @OneToMany(mappedBy = "route")
    private List<DisposalPlaces> disposalPlacesList = new ArrayList<>();
}

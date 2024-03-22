package com.example.eco_track_backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "truck_driver")
public class TruckDriver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String licenceNumber;
    private String email;
    private String password;
    private String phone;
    private String nic;
    private Integer age;
    private String role;

    @OneToMany(mappedBy = "truckDriver")
    private List<Route> routeList = new ArrayList<>();

}

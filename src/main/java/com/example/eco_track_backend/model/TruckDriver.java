package com.example.eco_track_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "truck_driver")
public class TruckDriver {

    @Id
    private Long id;
    private String licenceNumber;
    private String phone;
    private String nic;
    private Integer age;

    @OneToMany(mappedBy = "truckDriver")
    private List<Route> routeList = new ArrayList<>();

}

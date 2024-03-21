package com.example.eco_track_backend.request;

import com.example.eco_track_backend.model.ROLES;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class TruckDriverRequestDTO {

    private String name;
    private String licenceNumber;
    private String phone;
    private String nic;
    private Integer age;

    @Enumerated(EnumType.STRING)
    private ROLES role;
}

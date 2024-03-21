package com.example.eco_track_backend.request;

import com.example.eco_track_backend.model.ROLES;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class UserRegisterRequestDTO {
    private String email;
    private String name;
    private String username;
    private String password;
    private Integer age;
    private String nic;
    private Double latitude;
    private Double longitude;
    private String phone;

    @Enumerated(EnumType.STRING)
    private ROLES role;
}

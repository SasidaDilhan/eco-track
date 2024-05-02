package com.example.eco_track_backend.response;

import com.example.eco_track_backend.model.ROLES;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class UserResponseDTO {
    private String email;
    private String name;
    private String username;
    private String password;
    private Integer age;
    private String nic;
    private String phone;

    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private Long zip;

    @Enumerated(EnumType.STRING)
    private ROLES role;
}

package com.example.eco_track_backend.response;

import com.example.eco_track_backend.model.ROLES;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TruckDriverResponse {

    private Long id;

    private String name;

    private String licenceNumber;

    private String phone;

    private String nic;

    private Integer age;

    private String password;

    private String email;

    private ROLES role;
}

package com.example.eco_track_backend.request;

import com.example.eco_track_backend.model.ROLES;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserRegisterRequestDTO {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @NotNull(message = "Age cannot be null")
    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 99, message = "Age must be at most 99")
    private Integer age;

    @NotBlank(message = "NIC cannot be blank")
    @Pattern(regexp = "[0-9]{9}[vVxX]", message = "Invalid NIC format")
    private String nic;

    private Double latitude;

    private Double longitude;

    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    private String phone;

    @NotNull(message = "Role cannot be null")
    @Enumerated(EnumType.STRING)
    private ROLES role;
}

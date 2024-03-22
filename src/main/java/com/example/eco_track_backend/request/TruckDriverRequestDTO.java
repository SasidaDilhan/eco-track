package com.example.eco_track_backend.request;

import com.example.eco_track_backend.model.ROLES;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;


@Data
public class TruckDriverRequestDTO {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Licence number cannot be blank")
    private String licenceNumber;

    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    private String phone;

    @NotBlank(message = "NIC cannot be blank")
    @Pattern(regexp = "[0-9]{9}[vVxX]", message = "Invalid NIC format")
    private String nic;

    @NotNull(message = "Age cannot be null")
    @Size(min = 18, max = 99, message = "Age must be between 18 and 99")
    private Integer age;

    private String password;
    private String email;

    @NotNull(message = "Role cannot be null")
    @Enumerated(EnumType.STRING)
    private ROLES role;
}

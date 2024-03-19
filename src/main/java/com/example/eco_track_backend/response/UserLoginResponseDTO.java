package com.example.eco_track_backend.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginResponseDTO {

    private String token;
}

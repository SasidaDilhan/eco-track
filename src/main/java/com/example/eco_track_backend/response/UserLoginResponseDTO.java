package com.example.eco_track_backend.response;

import com.example.eco_track_backend.model.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginResponseDTO {

    private String token;
    private User user;
}

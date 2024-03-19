package com.example.eco_track_backend.request;

import lombok.Data;

@Data
public class UserAuthRequestDTO {

    private String username;
    private String password;
}

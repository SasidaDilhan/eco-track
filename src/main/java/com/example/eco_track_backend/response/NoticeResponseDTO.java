package com.example.eco_track_backend.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
public class NoticeResponseDTO {

    private Long id;
    private String description;
    private LocalDate date;
    private LocalTime time;
    private String imagePath;
}

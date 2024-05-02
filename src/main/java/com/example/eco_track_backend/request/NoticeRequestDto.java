package com.example.eco_track_backend.request;


import lombok.Data;



@Data
public class NoticeRequestDto {

    private Long id;
    private String description;
//    private LocalDate date;
    private String imagePath;
}

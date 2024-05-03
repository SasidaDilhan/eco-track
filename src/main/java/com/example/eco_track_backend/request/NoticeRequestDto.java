package com.example.eco_track_backend.request;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class NoticeRequestDto {

    private Long id;
    private String description;
//    private LocalDate date;
    private MultipartFile imagePath;
}

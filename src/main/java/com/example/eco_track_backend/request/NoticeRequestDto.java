package com.example.eco_track_backend.request;


import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class NoticeRequestDto {


    private String description;
    private MultipartFile imagePath;
}

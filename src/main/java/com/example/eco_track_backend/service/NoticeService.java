package com.example.eco_track_backend.service;

import com.example.eco_track_backend.exceptions.UserNotFonudException;
import com.example.eco_track_backend.model.Notice;
import com.example.eco_track_backend.model.User;
import com.example.eco_track_backend.request.NoticeRequestDto;
import org.springframework.http.ResponseEntity;

public interface NoticeService {

    void createNotice(NoticeRequestDto noticeRequestDto,User user);


}

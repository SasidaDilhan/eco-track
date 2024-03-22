package com.example.eco_track_backend.service;

import com.example.eco_track_backend.exceptions.UserNotFonudException;
import com.example.eco_track_backend.model.Notice;
import com.example.eco_track_backend.request.NoticeRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;

public interface NoticeService {

    void createNotice(NoticeRequestDto noticeRequestDto, String email)throws UserNotFonudException;


}

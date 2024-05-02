package com.example.eco_track_backend.service;

import com.example.eco_track_backend.exceptions.UserNotFonudException;
import com.example.eco_track_backend.model.Notice;
import com.example.eco_track_backend.request.NoticeRequestDto;
import com.example.eco_track_backend.response.NoticeResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface NoticeService {

    NoticeResponseDTO createNotice(NoticeRequestDto noticeRequestDto, String email)throws UserNotFonudException;


    List<NoticeResponseDTO> getAllNotice();
}

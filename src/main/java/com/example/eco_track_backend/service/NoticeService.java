package com.example.eco_track_backend.service;

import com.example.eco_track_backend.exceptions.NoticeNotFoundException;
import com.example.eco_track_backend.exceptions.UserNotFonudException;
import com.example.eco_track_backend.model.Notice;
import com.example.eco_track_backend.request.NoticeRequestDto;
import com.example.eco_track_backend.response.NoticeResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface NoticeService {

//    NoticeResponseDTO createNotice(NoticeRequestDto noticeRequestDto, String email)throws UserNotFonudException;


//    List<NoticeResponseDTO> getAllNotice();
//
//    NoticeResponseDTO updateSpecificNotice(Long noticeId, NoticeRequestDto noticeRequestDto)throws NoticeNotFoundException;
//
//    NoticeResponseDTO deleteSpecificNotice(Long noticeId)throws NoticeNotFoundException;
//
//    NoticeResponseDTO getSpecificNotice(Long noticeId)throws NoticeNotFoundException;

    NoticeResponseDTO create(NoticeRequestDto noticeRequestDto, MultipartFile file, String email)throws IOException;

    List<NoticeResponseDTO> getAllNotice();

    NoticeResponseDTO getSpecificNotice2(Long noticeId)throws NoticeNotFoundException;

    NoticeRequestDto deleteSpecificNotice2(Long noticeId)throws NoticeNotFoundException;
}

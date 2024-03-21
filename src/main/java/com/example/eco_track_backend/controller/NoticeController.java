package com.example.eco_track_backend.controller;

import com.example.eco_track_backend.exceptions.UserNotFonudException;
import com.example.eco_track_backend.model.Notice;
import com.example.eco_track_backend.model.User;
import com.example.eco_track_backend.request.NoticeRequestDto;
import com.example.eco_track_backend.service.NoticeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;


    @PostMapping("/admins/notices")
    public void createNotice(@RequestBody NoticeRequestDto noticeRequestDto,Authentication authentication){

        User user = (User) authentication.getPrincipal();

//        Long userId = user.getId();

        noticeService.createNotice(noticeRequestDto,user);
    }





}

package com.example.eco_track_backend.controller;

import com.example.eco_track_backend.exceptions.UserNotFonudException;
import com.example.eco_track_backend.model.Notice;
import com.example.eco_track_backend.request.NoticeRequestDto;
import com.example.eco_track_backend.response.NoticeResponseDTO;
import com.example.eco_track_backend.service.NoticeService;
import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;


    @RolesAllowed("ADMIN")
    @PostMapping(value = "/admins/notices",headers = "VERSION=V1")
    public NoticeResponseDTO createNotice(@RequestBody NoticeRequestDto noticeRequestDto, Authentication authentication)throws UserNotFonudException{

        User user = (User) authentication.getPrincipal();
        System.out.println("user email : "+user.getUsername());
        String email = user.getUsername();

       return noticeService.createNotice(noticeRequestDto,email);
    }



    @GetMapping("/notices")
    public List<NoticeResponseDTO> getAllNotice(){

        return noticeService.getAllNotice();
    }





}

package com.example.eco_track_backend.service.impl;

import com.example.eco_track_backend.exceptions.UserNotFonudException;
import com.example.eco_track_backend.model.Notice;
import com.example.eco_track_backend.model.User;
import com.example.eco_track_backend.repository.NoticeRepository;
import com.example.eco_track_backend.repository.UserRepository;
import com.example.eco_track_backend.request.NoticeRequestDto;
import com.example.eco_track_backend.response.NoticeResponseDTO;
import com.example.eco_track_backend.service.NoticeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final UserRepository userRepository;
    private final NoticeRepository noticeRepository;
    private ModelMapper modelMapper;


    @Override
    public NoticeResponseDTO createNotice(NoticeRequestDto noticeRequestDto, String email) throws UserNotFonudException {

        User user = userRepository.findUserByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("that email not found")
        );


        Notice notice = modelMapper.map(noticeRequestDto, Notice.class);
        notice.setDate(LocalDate.now());

        LocalTime time = LocalTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        String formattedTime = time.format(formatter);

        notice.setTime(LocalTime.parse(formattedTime));
        notice.setUser(user);

        noticeRepository.save(notice);

        return NoticeResponseDTO.builder().id(notice.getId()).date(notice.getDate()).description(notice.getDescription()).imagePath(notice.getImagePath()).time(notice.getTime()).build();
    }


}

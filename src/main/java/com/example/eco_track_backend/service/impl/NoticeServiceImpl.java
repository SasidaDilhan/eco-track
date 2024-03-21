package com.example.eco_track_backend.service.impl;

import com.example.eco_track_backend.exceptions.UserNotFonudException;
import com.example.eco_track_backend.model.Notice;
import com.example.eco_track_backend.model.User;
import com.example.eco_track_backend.repository.NoticeRepository;
import com.example.eco_track_backend.repository.UserRepository;
import com.example.eco_track_backend.request.NoticeRequestDto;
import com.example.eco_track_backend.service.NoticeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final UserRepository userRepository;
    private final NoticeRepository noticeRepository;
    private ModelMapper modelMapper;



  public void createNotice(NoticeRequestDto noticeRequestDto,User user){

      Notice notice = modelMapper.map(noticeRequestDto,Notice.class);

      notice.setUser(user);

      noticeRepository.save(notice);
  }
}

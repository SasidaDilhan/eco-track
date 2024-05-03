package com.example.eco_track_backend.service.impl;

import com.example.eco_track_backend.exceptions.NoticeNotFoundException;
import com.example.eco_track_backend.exceptions.UserNotFonudException;
import com.example.eco_track_backend.model.Notice;
import com.example.eco_track_backend.model.User;
import com.example.eco_track_backend.repository.NoticeRepository;
import com.example.eco_track_backend.repository.UserRepository;
import com.example.eco_track_backend.request.NoticeRequestDto;
import com.example.eco_track_backend.response.NoticeResponseDTO;
import com.example.eco_track_backend.service.NoticeService;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final UserRepository userRepository;
    private final NoticeRepository noticeRepository;
    private ModelMapper modelMapper;

    public static String uploadDirectory = System.getProperty("user.dir") + "/src/main/images/notices";


    @Override
    public NoticeResponseDTO create(NoticeRequestDto noticeRequestDto, MultipartFile file, String email) throws IOException {

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

        String originalFilename = file.getOriginalFilename();
        Path fileNameAndPath = Paths.get(uploadDirectory, originalFilename);
        Files.write(fileNameAndPath, file.getBytes());
        notice.setImagePath(originalFilename);

        noticeRepository.save(notice);

        return NoticeResponseDTO.builder().id(notice.getId()).date(notice.getDate()).time(notice.getTime()).description(notice.getDescription()).imagePath(notice.getImagePath()).build();

    }

    @Override
    public List<NoticeResponseDTO> getAllNotice() {

        List<Notice> noticeList = noticeRepository.findAll();

        return noticeList.stream().map(notice -> NoticeResponseDTO.builder().id(notice.getId()).description(notice.getDescription()).time(notice.getTime()).date(notice.getDate()).imagePath(notice.getImagePath()).build()).toList();
    }

    @Override
    public NoticeResponseDTO getSpecificNotice2(Long noticeId) throws NoticeNotFoundException {

        Notice notice = noticeRepository.findById(noticeId).orElseThrow(
                () -> new NoticeNotFoundException("that notice not in a database")

        );

        return NoticeResponseDTO.builder().id(notice.getId()).date(notice.getDate()).time(notice.getTime()).description(notice.getDescription()).imagePath(notice.getImagePath()).build();
    }

    @Override
    public NoticeRequestDto deleteSpecificNotice2(Long noticeId) throws NoticeNotFoundException {

        Notice notice = noticeRepository.findById(noticeId).orElseThrow(
                () -> new NoticeNotFoundException("that notice not in a database")

        );

        noticeRepository.deleteById(notice.getId());

        return null;
    }








//    @Override
//    public NoticeResponseDTO createNotice(NoticeRequestDto noticeRequestDto, String email) throws UserNotFonudException {
//
//        User user = userRepository.findUserByEmail(email).orElseThrow(
//                () -> new UsernameNotFoundException("that email not found")
//        );
//
//
//        Notice notice = modelMapper.map(noticeRequestDto, Notice.class);
//        notice.setDate(LocalDate.now());
//
//        LocalTime time = LocalTime.now();
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
//
//        String formattedTime = time.format(formatter);
//
//        notice.setTime(LocalTime.parse(formattedTime));
//        notice.setUser(user);
//
//        noticeRepository.save(notice);
//
//        return NoticeResponseDTO.builder().id(notice.getId()).date(notice.getDate()).description(notice.getDescription()).imagePath(notice.getImagePath()).time(notice.getTime()).build();
//    }








//    @Override
//    public List<NoticeResponseDTO> getAllNotice() {
//
//        List<Notice> noticeList = noticeRepository.findAll();
//
//       return noticeList.stream().map(notice -> NoticeResponseDTO.builder().time(notice.getTime()).date(notice.getDate()).imagePath(notice.getImagePath()).id(notice.getId()).description(notice.getDescription()).build()).toList();
//    }

//    @Override
//    public NoticeResponseDTO updateSpecificNotice(Long noticeId, NoticeRequestDto noticeRequestDto) throws NoticeNotFoundException {
//
//        Notice notice = noticeRepository.findById(noticeId).orElseThrow(
//                ()-> new NoticeNotFoundException("that notice not found")
//        );
//
//       notice.setDescription(noticeRequestDto.getDescription());
//       notice.setImagePath(noticeRequestDto.getImagePath());
//
//       noticeRepository.save(notice);
//
//       return NoticeResponseDTO.builder().time(notice.getTime()).date(notice.getDate()).imagePath(notice.getImagePath()).id(notice.getId()).description(notice.getDescription()).build();
//
//    }

//    @Override
//    public NoticeResponseDTO deleteSpecificNotice(Long noticeId) throws NoticeNotFoundException {
//
//        Notice notice = noticeRepository.findById(noticeId).orElseThrow(
//                ()-> new NoticeNotFoundException("that notice not in a database")
//        );
//
//        noticeRepository.deleteById(notice.getId());
//
//        return NoticeResponseDTO.builder().time(notice.getTime()).date(notice.getDate()).imagePath(notice.getImagePath()).id(notice.getId()).description(notice.getDescription()).build();
//    }
//
//    @Override
//    public NoticeResponseDTO getSpecificNotice(Long noticeId) throws NoticeNotFoundException {
//
//        Notice notice = noticeRepository.findById(noticeId).orElseThrow(
//                ()-> new NoticeNotFoundException("that notice not in a database")
//        );
//
//        return NoticeResponseDTO.builder().time(notice.getTime()).date(notice.getDate()).imagePath(notice.getImagePath()).id(notice.getId()).description(notice.getDescription()).build();
//
//
//    }



}

package com.example.eco_track_backend.repository;

import com.example.eco_track_backend.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice,Long> {
}

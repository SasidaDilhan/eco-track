package com.example.eco_track_backend.repository;

import com.example.eco_track_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

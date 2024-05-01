package com.example.eco_track_backend.repository;

import com.example.eco_track_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmail(String email);

    @Query("FROM User u WHERE u.email LIKE CONCAT('%', :email, '%')")
    List<User> findUsersByEmail(@Param("email") String email);
}

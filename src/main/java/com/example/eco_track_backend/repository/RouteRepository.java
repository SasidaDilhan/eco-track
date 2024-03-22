package com.example.eco_track_backend.repository;

import com.example.eco_track_backend.model.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Long> {
}

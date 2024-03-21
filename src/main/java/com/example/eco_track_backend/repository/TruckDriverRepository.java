package com.example.eco_track_backend.repository;

import com.example.eco_track_backend.model.TruckDriver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TruckDriverRepository extends JpaRepository<TruckDriver, Long> {
}

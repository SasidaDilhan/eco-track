package com.example.eco_track_backend.repository;

import com.example.eco_track_backend.model.DisposalPlaces;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisposalPlaceRepository extends JpaRepository<DisposalPlaces,Long> {
}

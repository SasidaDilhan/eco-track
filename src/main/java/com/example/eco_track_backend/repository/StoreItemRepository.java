package com.example.eco_track_backend.repository;

import com.example.eco_track_backend.model.StoreItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreItemRepository extends JpaRepository<StoreItem,Long> {
}

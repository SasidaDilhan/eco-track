package com.example.eco_track_backend.repository;

import com.example.eco_track_backend.model.StoreItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreItemRepository extends JpaRepository<StoreItem,Long> {

    List<StoreItem> findStoreItemsByUserId(Long userId);
}

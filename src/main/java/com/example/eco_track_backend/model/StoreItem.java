package com.example.eco_track_backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name ="stores")
public class StoreItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long quantity;

    @ManyToOne
    private User user;
}

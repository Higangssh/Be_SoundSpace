package com.the.soundspace.common.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@Entity
@Table(name = "studio_tbl", indexes = {
        @Index(name = "idx_location", columnList = "latitude, longitude")
})
public class StudioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "studio_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private double latitude;  // 위도

    @Column(nullable = false)
    private double longitude; // 경도

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private int capacity;

    @Column(name = "price_per_hour", nullable = false)
    private double pricePerHour;
}

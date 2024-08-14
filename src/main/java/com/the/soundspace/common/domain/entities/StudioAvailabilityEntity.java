package com.the.soundspace.common.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "studio_availability")
public class StudioAvailabilityEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "availability_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studio_id", nullable = false)
    private StudioEntity studio;

    @Column(name = "available_from", nullable = false)
    private String availableFrom;

    @Column(name = "available_to", nullable = false)
    private String availableTo;

    @Column(name = "day_of_week", nullable = false)
    private String dayOfWeek;
}
package com.the.soundspace.common.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "studio_facilities")
public class StudioFacilityEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "studio_id", nullable = false)
        private StudioEntity studio;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "facility_id", nullable = false)
        private FacilityEntity facility;

}

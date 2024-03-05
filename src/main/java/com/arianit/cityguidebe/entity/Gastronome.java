package com.arianit.cityguidebe.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "gastronomies")
public class Gastronome extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gastronome_id")
    private Long id;

    @Column(nullable = false)
    private String nameOfGastronome;

    @Column(length = 255)
    private String schedule;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    private Double latitude;

    @Enumerated(EnumType.STRING)
    private TypeOfGastronome typeOfGastronome;

    @ManyToOne
    @JoinColumn(name = "city_id")
    @JsonBackReference
    private City city;

    @OneToMany(mappedBy = "gastronome", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Reservation> reservations;

    @ElementCollection
    @CollectionTable(name = "gastronome_attachments", joinColumns = @JoinColumn(name = "gastronome_id"))
    @Column(name = "attachment")
    private List<String> attachments;
}
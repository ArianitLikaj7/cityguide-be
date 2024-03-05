package com.arianit.cityguidebe.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reservations")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Reservation {
    @Id
    @GeneratedValue
    private Long reservationId;

    @ManyToOne
    @JoinColumn(name = "gastronome_id")
    @JsonBackReference
    private Gastronome gastronome;

    private String reservationDate;

    private Integer numberOfPeople;

    private String specialRequests;

    @Column(nullable = false)
    private String phoneNumber;

    private String status;

}
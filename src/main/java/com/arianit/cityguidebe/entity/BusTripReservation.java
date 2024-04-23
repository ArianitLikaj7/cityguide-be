package com.arianit.cityguidebe.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "bustripreservations")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusTripReservation extends BaseEntity{

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "telephone")
    private Integer telephone;

    @Column(name = "username")
    private String username;

    @Column(name = "bustrip_id")
    private Long bustripId;

    @ManyToOne
    @JoinColumn(name = "bustrip_id", insertable = false, updatable = false)
    @JsonBackReference
    private BusTrip busTrip;
}

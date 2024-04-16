package com.arianit.cityguidebe.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Table(name = "bustrips")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BusTrip extends BaseEntity{

    @Column(name = "name_of_company", unique = true)
    private String nameOfCompany;

    @Column(name = "start_station")
    private String startStation;

    @Column(name = "destination")
    private String destination;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "price",precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "description",columnDefinition = "TEXT[]")
    private String [] description;


    @OneToMany(mappedBy = "busTrip")
    @JsonManagedReference
    private List<BusTripReservation> busTripReservations;



}

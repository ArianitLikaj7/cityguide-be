package com.arianit.cityguidebe.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Table(name = "bustrips")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BusTrip extends BaseEntity{

    @Column(name = "name_of_company")
    private String nameOfCompany;

    @Column(name = "start_station")
    private String startStation;

    @Column(name = "destination")
    private String destination;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "price",precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "description")
    private String description;



}

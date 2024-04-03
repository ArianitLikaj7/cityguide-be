package com.arianit.cityguidebe.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "bustrips")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BusTrip extends BaseEntity {

    @Column(name = "name_of_bus")
    private String nameOfBus;

    @Column(name = "number_of_days")
    private int numberOfDays;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;


}

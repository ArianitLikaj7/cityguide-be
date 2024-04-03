package com.arianit.cityguidebe.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "state_id", insertable = false, updatable = false)
    @JsonBackReference
    private State state;

    @Column(name = "state_id")
    private Long stateId;

}

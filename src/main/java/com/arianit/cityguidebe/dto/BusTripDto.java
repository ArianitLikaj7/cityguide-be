package com.arianit.cityguidebe.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusTripDto{
    private Long id;
    private String nameOfBus;
    private int numberOfDays;
    private BigDecimal price;
    private Long stateId;
}
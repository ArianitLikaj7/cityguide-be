package com.arianit.cityguidebe.dto;


import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusTripDto {
    private Long id;
    private String nameOfCompany;
    private String startStation;
    private String destination;
    private String startTime;
    private BigDecimal price;
    private String[] description;
}

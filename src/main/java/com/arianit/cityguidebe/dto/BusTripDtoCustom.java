package com.arianit.cityguidebe.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusTripDtoCustom {
    private Long id;
    private String nameOfCompany;
    private String startStation;
    private String destination;
    private String startTime;
    private BigDecimal price;
    private String description;
    private List<BusTripReservationDto> busTripReservationDto;
}

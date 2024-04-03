package com.arianit.cityguidebe.dto.request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public record BusTripRequest(
        String nameOfBus,
        int numberOfDays,
        BigDecimal price,
        Long stateId
) {
}

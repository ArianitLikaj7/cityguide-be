package com.arianit.cityguidebe.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record BusTripRequest(
       @NotNull String startStation,
       @NotNull String destination,
       @NotNull String startTime,
       @NotNull  BigDecimal price,
        String [] description
) {
}

package com.arianit.cityguidebe.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record BusTripRequest(
        @NotBlank  String nameOfCompany,
        String startStation,
        String destination,
        String startTime,
        BigDecimal price,
        String description
) {
}

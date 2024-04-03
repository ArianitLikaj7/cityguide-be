package com.arianit.cityguidebe.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CityRequest(
        Long stateId,
        @NotBlank String nameOfCity,
        @NotBlank String culturalHeritage,
        @NotBlank String cityPrefix,
        String taxiPhoneNumber
) {
}

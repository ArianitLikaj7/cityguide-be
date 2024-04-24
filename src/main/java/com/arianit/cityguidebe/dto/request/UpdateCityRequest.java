package com.arianit.cityguidebe.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateCityRequest(
        @NotNull Long id,
        @NotBlank String nameOfCity,
        @NotBlank String culturalHeritage,
        @NotBlank String cityPrefix,
        String taxiPhoneNumber
) {
}

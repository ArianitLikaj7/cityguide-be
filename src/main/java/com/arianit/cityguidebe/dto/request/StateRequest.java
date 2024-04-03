package com.arianit.cityguidebe.dto.request;

import jakarta.validation.constraints.NotBlank;

public record StateRequest(@NotBlank String nameOfState) {
}

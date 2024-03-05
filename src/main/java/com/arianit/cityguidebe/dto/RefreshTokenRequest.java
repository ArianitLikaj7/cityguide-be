package com.arianit.cityguidebe.dto;

import jakarta.validation.constraints.NotNull;

public record RefreshTokenRequest(@NotNull String token) {
}

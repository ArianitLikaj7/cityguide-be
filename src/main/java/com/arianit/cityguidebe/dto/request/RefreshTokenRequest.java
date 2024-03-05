package com.arianit.cityguidebe.dto.request;

import jakarta.validation.constraints.NotNull;

public record RefreshTokenRequest(@NotNull String token) {
}

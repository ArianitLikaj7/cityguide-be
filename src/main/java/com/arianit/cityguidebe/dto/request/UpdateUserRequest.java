package com.arianit.cityguidebe.dto.request;

import jakarta.validation.constraints.NotNull;

public record UpdateUserRequest(
        @NotNull Long id,
        @NotNull String firstName,
        @NotNull  String lastName,
        @NotNull String username
) {
}

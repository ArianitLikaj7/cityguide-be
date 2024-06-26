package com.arianit.cityguidebe.dto.request;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotNull;

public record BusTripReservationRequest(
        @NotNull Long busTripId,
        @NotNull String name,
        @NotNull String lastname,
        @NotNull Integer telephone
) {
}

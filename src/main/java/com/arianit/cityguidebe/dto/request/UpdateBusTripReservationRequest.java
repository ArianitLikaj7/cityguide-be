package com.arianit.cityguidebe.dto.request;

import jakarta.validation.constraints.NotNull;

public record UpdateBusTripReservationRequest(
        @NotNull Long id,
        @NotNull Long busTripId,
        @NotNull String name,
        @NotNull String lastname,
        @NotNull Integer telephone
) {
}

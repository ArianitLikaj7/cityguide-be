package com.arianit.cityguidebe.dto.request;

public record BusTripReservationRequest(
        Long busTripId,
        String name,
        String lastname
) {
}

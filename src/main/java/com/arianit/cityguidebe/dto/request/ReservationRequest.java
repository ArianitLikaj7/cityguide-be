package com.arianit.cityguidebe.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ReservationRequest(
        @NotBlank Long gastronomeId,
        String reservationDate,
        Integer numberOfPeople,
        String specialRequests,
        String phoneNumber,
        String status
) {
}

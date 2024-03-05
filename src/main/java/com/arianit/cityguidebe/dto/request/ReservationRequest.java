package com.arianit.cityguidebe.dto.request;

public record ReservationRequest(
        Long gastronomeId,
        String reservationDate,
        Integer numberOfPeople,
        String specialRequests,
        String phoneNumber,
        String status
) {
}

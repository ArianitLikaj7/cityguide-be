package com.arianit.cityguidebe.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto extends BaseDto{
    private Long reservationId;
    private Long gastronomeId;
    private String reservationDate;
    private Integer numberOfPeople;
    private String specialRequests;
    private String phoneNumber;
    private String status;
}
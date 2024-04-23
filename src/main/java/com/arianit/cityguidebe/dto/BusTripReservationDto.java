package com.arianit.cityguidebe.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BusTripReservationDto {
    private Long id;
    private String name;
    private String lastname;
    private String username;
    private Long  bustripId;
    private Integer telephone;
}

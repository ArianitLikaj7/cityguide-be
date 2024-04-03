package com.arianit.cityguidebe.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StateDto {
    private Long id;
    private String nameOfState;
    private List<BusTripDto> busTripDtos;

}

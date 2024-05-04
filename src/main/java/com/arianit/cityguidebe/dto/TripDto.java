package com.arianit.cityguidebe.dto;

import com.arianit.cityguidebe.entity.TypeOfGastronome;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TripDto {
    private Long id;
    private int numOfDays;
    private List<Long> cityIds;
    private List<TypeOfGastronome> typesOfGastronome;
    private List<List<CityDto>> cityDtosPerDay;
}
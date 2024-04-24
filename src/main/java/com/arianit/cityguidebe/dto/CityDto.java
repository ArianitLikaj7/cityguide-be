package com.arianit.cityguidebe.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import java.util.List;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CityDto extends BaseDto {
    private String nameOfCity;
    private String culturalHeritage;
    private String cityPrefix;
    private String [] taxiPhoneNumber;
    @JsonManagedReference
    private List<GastronomeDto> gastronomeDtos;
}

package com.arianit.cityguidebe.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.List;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"day", "id", "nameOfCity", "culturalHeritage", "cityPrefix", "taxiPhoneNumber", "gastronomeDtos"})
public class CityDto extends BaseDto {
    private String nameOfCity;
    private String culturalHeritage;
    private String cityPrefix;
    private String [] taxiPhoneNumber;
    @JsonManagedReference
    private List<GastronomeDto> gastronomeDtos;
    private int day;
}

package com.arianit.cityguidebe.mapper;

import com.arianit.cityguidebe.dto.CityDto;
import com.arianit.cityguidebe.dto.GastronomeDto;
import com.arianit.cityguidebe.dto.request.CityRequest;
import com.arianit.cityguidebe.entity.City;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CityMapper implements GenericMapper<City, CityDto, CityRequest> {

    private final ModelMapper modelMapper;

    @Override
    public CityDto toDto(City city) {
        CityDto cityDto = modelMapper.map(city, CityDto.class);
        if (city.getGastronomes() != null) {
            cityDto.setGastronomeDtos(city.getGastronomes()
                    .stream()
                    .map(gastronome -> modelMapper.map(gastronome, GastronomeDto.class))
                    .collect(Collectors.toList()));
        }
        return cityDto;
    }

    @Override
    public City toEntity(CityRequest request) {
        return modelMapper.map(request, City.class);
    }
}

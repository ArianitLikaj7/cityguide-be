package com.arianit.cityguidebe.mapper;

import com.arianit.cityguidebe.dto.CityDto;
import com.arianit.cityguidebe.dto.request.CityRequest;
import com.arianit.cityguidebe.entity.City;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CityMapper implements GenericMapper<City, CityDto, CityRequest> {

    private final ModelMapper modelMapper;
    @Override
    public CityDto toDto(City entity) {
        return modelMapper.map(entity, CityDto.class);
    }

    @Override
    public City toEntity(CityRequest request) {
        return modelMapper.map(request, City.class);
    }
}

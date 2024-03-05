package com.arianit.cityguidebe.mapper;

import com.arianit.cityguidebe.dto.TripDto;
import com.arianit.cityguidebe.dto.request.TripRequest;
import com.arianit.cityguidebe.entity.Trip;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TripMapper implements GenericMapper<Trip, TripDto, TripRequest> {
    private final ModelMapper modelMapper;

    @Override
    public TripDto toDto(Trip entity) {
        return modelMapper.map(entity, TripDto.class);
    }

    @Override
    public Trip toEntity(TripRequest request) {
        return modelMapper.map(request, Trip.class);
    }
}

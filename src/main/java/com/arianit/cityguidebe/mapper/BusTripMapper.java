package com.arianit.cityguidebe.mapper;

import com.arianit.cityguidebe.dto.BusTripDto;
import com.arianit.cityguidebe.dto.request.BusTripRequest;
import com.arianit.cityguidebe.entity.BusTrip;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BusTripMapper implements GenericMapper<BusTrip, BusTripDto, BusTripRequest> {

    private final ModelMapper modelMapper;

    @Override
    public BusTripDto toDto(BusTrip entity) {
        return modelMapper.map(entity, BusTripDto.class);
    }

    @Override
    public BusTrip toEntity(BusTripRequest request) {
        return modelMapper.map(request, BusTrip.class);
    }
}

package com.arianit.cityguidebe.mapper;

import com.arianit.cityguidebe.dto.BusTripReservationDto;
import com.arianit.cityguidebe.dto.request.BusTripReservationRequest;
import com.arianit.cityguidebe.entity.BusTripReservation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BusTripReservationMapper implements GenericMapper
        <BusTripReservation, BusTripReservationDto, BusTripReservationRequest>{

    private final ModelMapper mapper;
    @Override
    public BusTripReservationDto toDto(BusTripReservation entity) {
        return mapper.map(entity, BusTripReservationDto.class);
    }

    @Override
    public BusTripReservation toEntity(BusTripReservationRequest request) {
        return mapper.map(request, BusTripReservation.class);
    }
}

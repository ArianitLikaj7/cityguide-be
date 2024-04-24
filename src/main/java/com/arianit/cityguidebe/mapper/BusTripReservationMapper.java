package com.arianit.cityguidebe.mapper;

import com.arianit.cityguidebe.dto.BusTripReservationDto;
import com.arianit.cityguidebe.dto.request.BusTripReservationRequest;
import com.arianit.cityguidebe.dto.request.UpdateBusTripReservationRequest;
import com.arianit.cityguidebe.entity.BusTripReservation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BusTripReservationMapper implements UpdateGenericMapper
        <BusTripReservation, BusTripReservationDto, BusTripReservationRequest, UpdateBusTripReservationRequest>{

    private final ModelMapper modelMmapper;
    @Override
    public BusTripReservationDto toDto(BusTripReservation entity) {
        return modelMmapper.map(entity, BusTripReservationDto.class);
    }

    @Override
    public BusTripReservation toEntity(BusTripReservationRequest request) {
        return modelMmapper.map(request, BusTripReservation.class);
    }

    @Override
    public void toEntity(UpdateBusTripReservationRequest updateRequest, BusTripReservation entity) {
        modelMmapper.map(updateRequest, entity);
    }
}

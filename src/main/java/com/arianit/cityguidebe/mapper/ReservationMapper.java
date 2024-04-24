package com.arianit.cityguidebe.mapper;

import com.arianit.cityguidebe.dto.ReservationDto;
import com.arianit.cityguidebe.dto.request.ReservationRequest;
import com.arianit.cityguidebe.dto.request.UpdateReservationRequest;
import com.arianit.cityguidebe.entity.Reservation;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservationMapper implements UpdateGenericMapper<Reservation,
        ReservationDto, ReservationRequest, UpdateReservationRequest> {
    private final ModelMapper modelMapper;

    @Override
    public ReservationDto toDto(Reservation entity) {
        return modelMapper.map(entity, ReservationDto.class);
    }

    @Override
    public Reservation toEntity(ReservationRequest request) {
        return modelMapper.map(request, Reservation.class);
    }

    @Override
    public void toEntity(UpdateReservationRequest updateRequest, Reservation entity) {
        modelMapper.map(updateRequest, entity);
    }
}

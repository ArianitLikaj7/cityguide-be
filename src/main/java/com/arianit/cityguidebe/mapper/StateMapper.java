package com.arianit.cityguidebe.mapper;

import com.arianit.cityguidebe.dto.BusTripDto;
import com.arianit.cityguidebe.dto.StateDto;
import com.arianit.cityguidebe.dto.request.StateRequest;
import com.arianit.cityguidebe.entity.State;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StateMapper implements GenericMapper<State, StateDto, StateRequest>{

    private final ModelMapper modelMapper;

    @Override
    public StateDto toDto(State entity) {
        StateDto stateDto = modelMapper.map(entity, StateDto.class);
        if (entity.getBusTrips() != null) {
            stateDto.setBusTripDtos(
                    entity.getBusTrips().stream()
                            .map(busTrip -> modelMapper.map(busTrip, BusTripDto.class))
                            .collect(Collectors.toList())
            );
        }
        return stateDto;
    }

    @Override
    public State toEntity(StateRequest request) {
        return modelMapper.map(request, State.class);
    }
}

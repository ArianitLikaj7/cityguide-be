package com.arianit.cityguidebe.mapper;

import com.arianit.cityguidebe.dto.BusTripDtoCustom;
import com.arianit.cityguidebe.entity.BusTrip;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BusTripDtoCustomMapper {
    private final ModelMapper mapper;


    public BusTripDtoCustom toDtoCustom(BusTrip entity) {
        return mapper.map(entity, BusTripDtoCustom.class);
    }
}

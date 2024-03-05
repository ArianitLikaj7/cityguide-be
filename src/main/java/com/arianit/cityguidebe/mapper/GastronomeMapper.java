package com.arianit.cityguidebe.mapper;

import com.arianit.cityguidebe.dto.GastronomeDto;
import com.arianit.cityguidebe.dto.request.GastronomeRequest;
import com.arianit.cityguidebe.entity.Gastronome;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GastronomeMapper implements GenericMapper<Gastronome, GastronomeDto, GastronomeRequest>{

    private final ModelMapper modelMapper;
    @Override
    public GastronomeDto toDto(Gastronome entity) {
        return modelMapper.map(entity, GastronomeDto.class);
    }

    @Override
    public Gastronome toEntity(GastronomeRequest request) {
        return modelMapper.map(request, Gastronome.class);
    }
}

package com.arianit.cityguidebe.mapper;

import com.arianit.cityguidebe.dto.FavoriteDto;
import com.arianit.cityguidebe.dto.request.FavoriteRequest;
import com.arianit.cityguidebe.entity.Favorite;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FavoriteMapper implements GenericMapper<Favorite, FavoriteDto, FavoriteRequest>{

    private final ModelMapper mapper;

    @Override
    public FavoriteDto toDto(Favorite entity) {
        FavoriteDto dto = mapper.map(entity, FavoriteDto.class);
        List<Long> gastronomeIds = Collections.singletonList(entity.getGastronomeId());
        dto.setGastronomeDtoList(gastronomeIds);
        return dto;
    }


    @Override
    public Favorite toEntity(FavoriteRequest request) {
        return mapper.map(request, Favorite.class);
    }
}

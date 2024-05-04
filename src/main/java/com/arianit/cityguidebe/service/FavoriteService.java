package com.arianit.cityguidebe.service;

import com.arianit.cityguidebe.dao.FavoriteRepository;
import com.arianit.cityguidebe.dto.FavoriteDto;
import com.arianit.cityguidebe.dto.GastronomeDto;
import com.arianit.cityguidebe.dto.UserDto;
import com.arianit.cityguidebe.dto.request.FavoriteRequest;
import com.arianit.cityguidebe.entity.Favorite;
import com.arianit.cityguidebe.entity.Gastronome;
import com.arianit.cityguidebe.exception.ResourceNotFoundException;
import com.arianit.cityguidebe.mapper.FavoriteMapper;
import com.arianit.cityguidebe.mapper.GastronomeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoriteService {
    private final FavoriteRepository favoriteRepository;
    private final FavoriteMapper mapper;
    private final GastronomeService gastronomeService;
    private final GastronomeMapper gastronomeMapper;


    public List<GastronomeDto> getUserFavoriteGastronomies(String username) {
        List<Favorite> favorites = favoriteRepository.findByNameOfUser(username);
        List<Long> gastronomesIds = favorites.stream()
                .map(Favorite::getGastronomeId)
                .collect(Collectors.toList());
        return gastronomeService.getAllByIds(gastronomesIds);
    }

    public FavoriteDto create(FavoriteRequest request){
        Favorite favorite = mapper.toEntity(request);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        favorite.setNameOfUser(username);
        mapGastronomeToFavorite(request,favorite);
        // TODO check if in databae then save
        return mapper.toDto(favoriteRepository.save(favorite));
    }

    public FavoriteDto getById(Long id){
        Favorite favoriteInDb = favoriteRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(
                        String.format("Favorite with %s id not found", id)
                )
        );
        return mapper.toDto(favoriteInDb);
    }

    public List<FavoriteDto> getAll (){
        List<Favorite> favorites = favoriteRepository.findAll();
        return favorites.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public void deleteById(Long id){
        Favorite favoriteInDb = favoriteRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(
                        String.format("Favorite with %s id not found", id)
                )
        );
        favoriteRepository.deleteById(id);
    }
    private void mapGastronomeToFavorite(FavoriteRequest request, Favorite favorite){
        GastronomeDto gastronomeDto = gastronomeService.getById(request.gastronomeId());
        favorite.setGastronomeId(gastronomeDto.getId());
    }

    public List<Long> findGastronomeIdByNameOfUser(String username) {
        return favoriteRepository.findGastronomeIdByNameOfUser(username);
    }
}

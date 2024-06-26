package com.arianit.cityguidebe.service;

import com.arianit.cityguidebe.dao.CityRepository;
import com.arianit.cityguidebe.dao.GastronomeRepository;
import com.arianit.cityguidebe.dto.CityDto;
import com.arianit.cityguidebe.dto.GastronomeDto;
import com.arianit.cityguidebe.dto.UserDto;
import com.arianit.cityguidebe.dto.request.GastronomeRequest;
import com.arianit.cityguidebe.dto.request.PageRequest;
import com.arianit.cityguidebe.dto.request.UpdateGastronomeRequest;
import com.arianit.cityguidebe.entity.City;
import com.arianit.cityguidebe.entity.Gastronome;
import com.arianit.cityguidebe.entity.TypeOfGastronome;
import com.arianit.cityguidebe.entity.User;
import com.arianit.cityguidebe.exception.MismatchedInputException;
import com.arianit.cityguidebe.exception.ResourceNotFoundException;
import com.arianit.cityguidebe.mapper.GastronomeMapper;
import com.arianit.cityguidebe.util.ReflectionUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class GastronomeService {
    private final CityRepository cityRepository;
    private final GastronomeRepository gastronomeRepository;
    private final GastronomeMapper gastronomeMapper;
    private final CityService cityService;


    public GastronomeDto create (GastronomeRequest gastronomeRequest){
        City cityInDb = cityRepository.findById(gastronomeRequest.cityId())
                .orElseThrow(()-> new ResourceNotFoundException(
                        String.format("City with %s id not found",gastronomeRequest.cityId())
                ));
        Gastronome gastronome = gastronomeMapper.toEntity(gastronomeRequest);
        mapCityToGastronome(gastronomeRequest,gastronome);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User loggedUser = (User) authentication.getPrincipal();
        gastronome.setUserId(loggedUser.getId());
        Gastronome gastronomeInDb = gastronomeRepository.save(gastronome);
        return gastronomeMapper.toDto(gastronomeInDb);
    }

    public List<GastronomeDto> createAll(List<GastronomeRequest> requests){
        List<GastronomeDto> gastronomeDtos = new ArrayList<>();
        for(GastronomeRequest r : requests){
            try{
            gastronomeDtos.add(create(r));
        }catch (Exception e){
                System.out.println("Failed to create Gastronome for request: " + e.getMessage());
            }
        }
        return gastronomeDtos;
    }

    public GastronomeDto getById(Long id){
        Gastronome gastronomeInDb = gastronomeRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException(
                        String.format("Gastronome with %s id not found",id)
                ));
        return gastronomeMapper.toDto(gastronomeInDb);
    }

    public List<GastronomeDto> getAll(){
        List<Gastronome> gastronomes = gastronomeRepository.findAll();
        return gastronomes.stream()
                .map(gastronomeMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<GastronomeDto> getAllByIds(List<Long> ids){
        List<Gastronome> gastronomesByIds = gastronomeRepository.findAllById(ids);
        return gastronomesByIds.stream()
                .map(gastronomeMapper::toDto)
                .collect(Collectors.toList());
    }

    public Page<GastronomeDto> getAllPagable(@Valid PageRequest pageRequest){
        return gastronomeRepository.findAll(pageRequest.getPageable()).map(
                gastronomeMapper::toDto
        );
    }

    public void delete (Long id) {
        Gastronome gastronomeInDb = gastronomeRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException(
                        String.format("Gastronome with %s id not found",id)
                ));
        gastronomeRepository.deleteById(id);
    }

    public GastronomeDto update(Long id, UpdateGastronomeRequest request){
        if (!(id.equals(request.id()))){
            throw new MismatchedInputException("Ids dosent match");
        }

        Gastronome gastronomeInDb = gastronomeRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException(
                        String.format("Gastronmoe with %s id not found", id)));

        gastronomeMapper.toEntity(request,gastronomeInDb);
        mapCityToUpdateGastronome(request,gastronomeInDb);
        return gastronomeMapper.toDto(gastronomeRepository.save(gastronomeInDb));
    }

    public List<GastronomeDto> getGastronomesByCityId(Long cityId){
        List<Gastronome> gastronomes = gastronomeRepository.findByCityId(cityId);
        return gastronomes.stream()
                .map(gastronomeMapper::toDto)
                .collect(toList());
    }

    public List<GastronomeDto> getGastronomesByUserId(Long cityId){
        List<Gastronome> gastronomes = gastronomeRepository.findByUserId(cityId);
        return gastronomes.stream()
                .map(gastronomeMapper::toDto)
                .collect(toList());
    }

    public List<GastronomeDto> getGastronomesByCityIdAndTypes(Long cityId, List<TypeOfGastronome> types) {
        List<Gastronome> gastronomes = gastronomeRepository.findAll().stream()
                .filter(gastronome -> gastronome.getCityId().equals(cityId) && types.contains(gastronome.getTypeOfGastronome()))
                .sorted(Comparator.comparing(Gastronome::isSponsored).reversed())
                .collect(Collectors.toList());
        return gastronomes.stream()
                .map(gastronomeMapper::toDto)
                .collect(Collectors.toList());
    }
    public List<GastronomeDto> getTheMostVisitedGastronomes() {
        List<Gastronome> gastronomes = gastronomeRepository.findAll();
        Set<GastronomeDto> sponsoredGastronomesSet = new HashSet<>(gastronomes.stream()
                .filter(Gastronome::isSponsored)
                .map(gastronomeMapper::toDto)
                .collect(Collectors.toList()));
        return new ArrayList<>(sponsoredGastronomesSet);
    }

    private void mapCityToGastronome(GastronomeRequest gastronomeRequest, Gastronome gastronome) {
        CityDto city = cityService.getById(gastronomeRequest.cityId());
        gastronome.setCityId(city.getId());
    }

    private void mapCityToUpdateGastronome(UpdateGastronomeRequest request, Gastronome gastronome) {
        if(request.cityId() != null){
        CityDto city = cityService.getById(request.cityId());
        gastronome.setCityId(city.getId());
        }
    }
}

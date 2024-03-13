package com.arianit.cityguidebe.service;

import com.arianit.cityguidebe.dao.CityRepository;
import com.arianit.cityguidebe.dao.GastronomeRepository;
import com.arianit.cityguidebe.dto.CityDto;
import com.arianit.cityguidebe.dto.GastronomeDto;
import com.arianit.cityguidebe.dto.request.GastronomeRequest;
import com.arianit.cityguidebe.dto.request.PageRequest;
import com.arianit.cityguidebe.entity.City;
import com.arianit.cityguidebe.entity.Gastronome;
import com.arianit.cityguidebe.exception.ResourceNotFoundException;
import com.arianit.cityguidebe.mapper.GastronomeMapper;
import com.arianit.cityguidebe.util.ReflectionUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
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
        Gastronome gastronomeInDb = gastronomeRepository.save(gastronome);
        return gastronomeMapper.toDto(gastronomeInDb);
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

    public GastronomeDto update(Long id, Map<String, Object> fields) {
        Gastronome gastronomeInDb = gastronomeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("city with %s not found", id)
                ));
        fields.forEach((key, value) ->{
            ReflectionUtil.setFieldValue(gastronomeInDb, key, value);
        });
        return gastronomeMapper.toDto(gastronomeRepository.save(gastronomeInDb));
    }
    public List<GastronomeDto> getGastronomesByCityId(Long cityId){
        List<Gastronome> gastronomes = gastronomeRepository.findByCityId(cityId);
        return gastronomes.stream()
                .map(gastronomeMapper::toDto)
                .collect(toList());
    }

    public List<GastronomeDto> getTheMostVisitedGastronomes() {
        List<Gastronome> gastronomes = gastronomeRepository.findAll();
        List<GastronomeDto> sponsoredGastronomes = gastronomes.stream()
                .filter(Gastronome::isSponsored)
                .map(gastronomeMapper::toDto)
                .collect(Collectors.toList());
        return sponsoredGastronomes;
    }

    private void mapCityToGastronome(GastronomeRequest gastronomeRequest, Gastronome gastronome) {
        CityDto city = cityService.getById(gastronomeRequest.cityId());
        gastronome.setCityId(city.getId());
    }

}

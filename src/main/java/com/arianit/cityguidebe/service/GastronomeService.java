package com.arianit.cityguidebe.service;

import com.arianit.cityguidebe.dao.CityRepository;
import com.arianit.cityguidebe.dao.GastronomeRepository;
import com.arianit.cityguidebe.dto.GastronomeDto;
import com.arianit.cityguidebe.dto.request.GastronomeRequest;
import com.arianit.cityguidebe.entity.City;
import com.arianit.cityguidebe.entity.Gastronome;
import com.arianit.cityguidebe.exception.ResourceNotFoundException;
import com.arianit.cityguidebe.mapper.GastronomeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GastronomeService {
    private final CityRepository cityRepository;
    private final GastronomeRepository gastronomeRepository;
    private final GastronomeMapper gastronomeMapper;

    public GastronomeDto create (GastronomeRequest gastronomeRequest){
        City cityInDb = cityRepository.findById(gastronomeRequest.cityId())
                .orElseThrow(()-> new ResourceNotFoundException(
                        String.format("City with %s id not found",gastronomeRequest.cityId())
                ));
        Gastronome gastronome = gastronomeMapper.toEntity(gastronomeRequest);
        gastronome.setCityId(gastronomeRequest.cityId());
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

    public void delete (Long id) {
        Gastronome gastronomeInDb = gastronomeRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException(
                        String.format("Gastronome with %s id not found",id)
                ));
        gastronomeRepository.deleteById(id);
    }
}

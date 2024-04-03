package com.arianit.cityguidebe.service;

import com.arianit.cityguidebe.dao.CityRepository;
import com.arianit.cityguidebe.dao.StateRepository;
import com.arianit.cityguidebe.dto.CityDto;
import com.arianit.cityguidebe.dto.StateDto;
import com.arianit.cityguidebe.dto.request.CityRequest;
import com.arianit.cityguidebe.dto.request.GastronomeRequest;
import com.arianit.cityguidebe.dto.request.PageRequest;
import com.arianit.cityguidebe.entity.City;
import com.arianit.cityguidebe.entity.Gastronome;
import com.arianit.cityguidebe.entity.State;
import com.arianit.cityguidebe.exception.ResourceNotFoundException;
import com.arianit.cityguidebe.mapper.CityMapper;
import com.arianit.cityguidebe.util.ReflectionUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityService {
    private final CityRepository cityRepository;
    private final CityMapper cityMapper;
    private final StateRepository stateRepository;
    private final StateService stateService;

    public CityDto create(CityRequest cityRequest) {
        State stateInDb = stateRepository.findById(cityRequest.stateId()).orElseThrow(
                () -> new ResourceNotFoundException(
                        String.format("State with id %s not found",cityRequest.stateId()))
        );
        City city = cityMapper.toEntity(cityRequest);
        mapStateToCity(cityRequest,city);
        city = cityRepository.save(city);
        return cityMapper.toDto(city);
    }

    public CityDto getById(Long id) {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("city with %s not found", id)
                ));
        return cityMapper.toDto(city);
    }

    public List<CityDto> getAll() {
        List<City> cities = cityRepository.findAll();
        return cities.stream()
                .map(cityMapper::toDto)
                .collect(Collectors.toList());
    }
    public Page<CityDto> getAllPagable(@Valid PageRequest pageRequest){
        return cityRepository.findAll(pageRequest.getPageable()).map(
                cityMapper::toDto
        );
    }

    public CityDto update(Long id, Map<String, Object> fields) {
        City cityInDb = cityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("city with %s not found", id)
                ));
        fields.forEach((key, value) ->{
            ReflectionUtil.setFieldValue(cityInDb, key, value);
        });
        return cityMapper.toDto(cityRepository.save(cityInDb));
    }

    public CityDto findCitiesByCityPrefix(String cityPrefix) {
        City city = cityRepository.findCitiesByCityPrefix(cityPrefix);
        CityDto cityDto = cityMapper.toDto(city);
        return cityDto;
    }

    public void delete(Long id) {
        City cityInDb = cityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("city with %s not found", id)
                ));
        cityRepository.deleteById(id);
    }

    private void mapStateToCity(CityRequest cityRequest, City city) {
        StateDto state = stateService.getById(cityRequest.stateId());
        city.setStateId(state.getId());
    }

}

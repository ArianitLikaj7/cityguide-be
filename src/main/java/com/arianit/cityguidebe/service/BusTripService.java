package com.arianit.cityguidebe.service;

import com.arianit.cityguidebe.dao.BusTripRepository;
import com.arianit.cityguidebe.dto.BusTripDto;
import com.arianit.cityguidebe.dto.request.BusTripRequest;
import com.arianit.cityguidebe.entity.BusTrip;
import com.arianit.cityguidebe.exception.ResourceNotFoundException;
import com.arianit.cityguidebe.mapper.BusTripMapper;

import com.arianit.cityguidebe.util.ReflectionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BusTripService {

    private final BusTripRepository busTripRepository;
    private final BusTripMapper busTripMapper;


    public BusTripDto create(BusTripRequest createBusTripRequest) {
        BusTrip busTrip = busTripMapper.toEntity(createBusTripRequest);
        busTrip = busTripRepository.save(busTrip);
        return busTripMapper.toDto(busTrip);
    }


    public BusTripDto getById(Long id) {
        BusTrip busTrip = busTripRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("BusTrip with id %s not found", id)
                ));
        return busTripMapper.toDto(busTrip);
    }


    public List<BusTripDto> getAll() {
        List<BusTrip> busTrips = busTripRepository.findAll();
        return busTrips.stream()
                .map(busTripMapper::toDto)
                .collect(Collectors.toList());
    }

    public BusTripDto update(Long id, Map<String, Object> fields) {
        BusTrip busTripInDb = busTripRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("BusTrip with id %s not found", id)
                ));
        fields.forEach((key, value) ->{
            ReflectionUtil.setFieldValue(busTripInDb, key, value);
        });

        return busTripMapper.toDto(busTripInDb);
    }

    public void delete(Long id) {
        busTripRepository.deleteById(id);
    }
}

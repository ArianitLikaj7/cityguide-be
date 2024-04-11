package com.arianit.cityguidebe.service;


import com.arianit.cityguidebe.dao.BusTripRepository;
import com.arianit.cityguidebe.dto.BusTripDto;
import com.arianit.cityguidebe.dto.UserDto;
import com.arianit.cityguidebe.dto.request.BusTripRequest;
import com.arianit.cityguidebe.dto.request.PageRequest;
import com.arianit.cityguidebe.entity.BusTrip;
import com.arianit.cityguidebe.exception.ResourceNotFoundException;
import com.arianit.cityguidebe.mapper.BusTripMapper;
import com.arianit.cityguidebe.util.ReflectionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BusTripService {

    private final BusTripRepository busTripRepository;
    private final BusTripMapper mapper;
    private final UserService userService;

    public BusTripDto create(BusTripRequest request){
        BusTrip busTrip = mapper.toEntity(request);
        UserDto currentUser = userService.getCurrentUser();
        busTrip.setNameOfCompany(currentUser.getFirstName());
        return mapper.toDto(busTripRepository.save(busTrip));
    }

    public BusTripDto getById(Long id){
        BusTrip busTripInDb = busTripRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException(String.format(
                        "Bus Trip with id %s not found",id
                ))
        );
        return mapper.toDto(busTripInDb);
    }

    public List<BusTripDto> getByStartStationAndDestination(String startStatiton, String destination){
        List<BusTrip> busTrips = busTripRepository.findByStartStationAndDestination(startStatiton,destination);
        return busTrips.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public Page<BusTripDto> getByStartStationAndDestinationPageable(
            String startStatiton, String destination, PageRequest pageRequest) {
        Page<BusTrip> allBusTrips = busTripRepository.findByStartStationAndDestination(
                startStatiton,destination,pageRequest.getPageable());
        return allBusTrips.map(
                mapper::toDto
        );
    }
    public List<BusTripDto> getAll() {
        List<BusTrip> busTrips = busTripRepository.findAll();
        return busTrips.stream()
                .map(mapper::toDto)
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

        return mapper.toDto(busTripInDb);
    }

    public void delete(Long id) {
        busTripRepository.deleteById(id);
    }
}

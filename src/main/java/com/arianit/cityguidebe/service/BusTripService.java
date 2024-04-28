package com.arianit.cityguidebe.service;


import com.arianit.cityguidebe.dao.BusTripRepository;
import com.arianit.cityguidebe.dto.BusTripDto;
import com.arianit.cityguidebe.dto.BusTripDtoCustom;
import com.arianit.cityguidebe.dto.UserDto;
import com.arianit.cityguidebe.dto.request.BusTripRequest;
import com.arianit.cityguidebe.dto.request.PageRequest;
import com.arianit.cityguidebe.dto.request.UpdateBusTripRequest;
import com.arianit.cityguidebe.entity.BusTrip;
import com.arianit.cityguidebe.exception.MismatchedInputException;
import com.arianit.cityguidebe.exception.ResourceNotFoundException;
import com.arianit.cityguidebe.mapper.BusTripDtoCustomMapper;
import com.arianit.cityguidebe.mapper.BusTripMapper;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BusTripService {

    private final BusTripRepository busTripRepository;
    private final BusTripMapper busTripMapper;
    private final UserService userService;
    private final BusTripDtoCustomMapper customMapper;

    @Transactional
    public BusTripDto create(BusTripRequest request){
        BusTrip busTrip = busTripMapper.toEntity(request);
        UserDto currentUser = userService.getCurrentUser();
        busTrip.setNameOfCompany(currentUser.getFirstName());
        return busTripMapper.toDto(busTripRepository.save(busTrip));
    }

    public BusTripDto getById(Long id){
        BusTrip busTripInDb = busTripRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException(String.format(
                        "Bus Trip with id %s not found",id
                ))
        );
        return busTripMapper.toDto(busTripInDb);
    }

    public List<BusTripDto> getByStartStationAndDestination(String startStatiton, String destination){
        List<BusTrip> busTrips = busTripRepository.findByStartStationAndDestination(startStatiton,destination);
        return busTrips.stream()
                .map(busTripMapper::toDto)
                .collect(Collectors.toList());
    }

    public Page<BusTripDto> getByStartStationAndDestinationPageable(
            String startStatiton, String destination, PageRequest pageRequest) {
        Page<BusTrip> allBusTrips = busTripRepository.findByStartStationAndDestination(
                startStatiton,destination,pageRequest.getPageable());
        return allBusTrips.map(
                busTripMapper::toDto
        );
    }

    public List<BusTripDto> getAll() {
        List<BusTrip> busTrips = busTripRepository.findAll();
        return busTrips.stream()
                .map(busTripMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<BusTripDto> getAllByNameOfCompany() {
        UserDto currentUser = userService.getCurrentUser();
        List<BusTrip> busTrips = busTripRepository.findByNameOfCompany(currentUser.getFirstName());
        return busTrips.stream()
                .map(busTripMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<BusTripDtoCustom> getAllWithReservation() {
        UserDto currentUser = userService.getCurrentUser();
        List<BusTrip> busTrips =  busTripRepository.findByNameOfCompany(currentUser.getFirstName());
        return busTrips.stream()
                .map(customMapper::toDtoCustom)
                .collect(Collectors.toList());
    }


    public BusTripDto update (Long id, UpdateBusTripRequest request){
        if(!id.equals(request.id())){
            throw new MismatchedInputException("Ids dosent matchs");
        }
        BusTrip busTripInDb = busTripRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException(String.format("BusTrip with %s id not found",id))
        );
        busTripMapper.toEntity(request,busTripInDb);
        return busTripMapper.toDto(busTripRepository.save(busTripInDb));

    }

    public void delete(Long id) {
        busTripRepository.deleteById(id);
    }
}

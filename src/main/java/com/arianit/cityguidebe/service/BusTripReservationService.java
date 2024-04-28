package com.arianit.cityguidebe.service;

import com.arianit.cityguidebe.dao.BusTripReservationRepository;
import com.arianit.cityguidebe.dto.BusTripDto;
import com.arianit.cityguidebe.dto.BusTripReservationDto;
import com.arianit.cityguidebe.dto.GastronomeDto;
import com.arianit.cityguidebe.dto.UserDto;
import com.arianit.cityguidebe.dto.request.BusTripReservationRequest;
import com.arianit.cityguidebe.dto.request.UpdateBusTripReservationRequest;
import com.arianit.cityguidebe.dto.request.UpdateGastronomeRequest;
import com.arianit.cityguidebe.entity.BusTripReservation;
import com.arianit.cityguidebe.entity.Gastronome;
import com.arianit.cityguidebe.exception.MismatchedInputException;
import com.arianit.cityguidebe.exception.ResourceNotFoundException;
import com.arianit.cityguidebe.mapper.BusTripReservationMapper;
import com.arianit.cityguidebe.util.ReflectionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BusTripReservationService {

    private final BusTripReservationRepository busTripReservationRepository;
    private final UserService userService;
    private final BusTripReservationMapper busTripReservationMapper;
    private final BusTripService busTripService;

    @Transactional
    public BusTripReservationDto create (BusTripReservationRequest request){
        BusTripReservation busTripReservation = busTripReservationMapper.toEntity(request);
        mapBusTripToBusTripReservation(request, busTripReservation);
        UserDto currentUser = userService.getCurrentUser();
        busTripReservation.setUsername(currentUser.getUsername());
        return busTripReservationMapper.toDto(busTripReservationRepository.save(busTripReservation));
    }

    public List<BusTripReservationDto> getAll(){
        List<BusTripReservation> busTripReservations = busTripReservationRepository.findAll();
        return busTripReservations.stream()
                .map(busTripReservationMapper::toDto)
                .collect(Collectors.toList());
    }

    public BusTripReservationDto getById(Long id){
        BusTripReservation busTripReservationInDb = busTripReservationRepository
                .findById(id).orElseThrow(()-> new ResourceNotFoundException(
                        String.format("BusTripReservation with id %s not found", id)
                ));
        return busTripReservationMapper.toDto(busTripReservationInDb);
    }

    public void delete (Long id){
        BusTripReservation busTripReservationInDb = busTripReservationRepository
                .findById(id).orElseThrow(()-> new ResourceNotFoundException(
                        String.format("BusTripReservation with id %s not found", id)
                ));
        busTripReservationRepository.deleteById(id);
    }


    public BusTripReservationDto update(Long id, UpdateBusTripReservationRequest request){
        if (!(id.equals(request.id()))){
            throw new MismatchedInputException("Ids dosent match");
        }
        BusTripReservation busTripReservationInDb = busTripReservationRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException(
                        String.format("Gastronmoe with %s id not found", id)));

        busTripReservationMapper.toEntity(request,busTripReservationInDb);
        mapBusTripToUpdateBusTripReservation(request,busTripReservationInDb);
        return busTripReservationMapper.toDto(busTripReservationRepository.save(busTripReservationInDb));
    }


    public List<BusTripReservationDto> findBusTripReservationByUsername() {
        UserDto currentUser = userService.getCurrentUser();
        List<BusTripReservation> busTripReservations =
                busTripReservationRepository.findBusTripReservationByUsername(currentUser.getUsername());
        return busTripReservations.stream()
                .map(busTripReservationMapper::toDto)
                .collect(Collectors.toList());
    }

    public void mapBusTripToBusTripReservation(BusTripReservationRequest req, BusTripReservation busTripReservation){
        BusTripDto busTripDto = busTripService.getById(req.busTripId());
        busTripReservation.setBustripId(busTripDto.getId());
    }

    public void mapBusTripToUpdateBusTripReservation(UpdateBusTripReservationRequest req, BusTripReservation busTripReservation){
        if(req.busTripId() != null){
        BusTripDto busTripDto = busTripService.getById(req.busTripId());
        busTripReservation.setBustripId(busTripDto.getId());
        }
    }
}

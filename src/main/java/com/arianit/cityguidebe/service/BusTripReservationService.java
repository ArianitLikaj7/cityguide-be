package com.arianit.cityguidebe.service;

import com.arianit.cityguidebe.dao.BusTripReservationRepository;
import com.arianit.cityguidebe.dto.BusTripDto;
import com.arianit.cityguidebe.dto.BusTripReservationDto;
import com.arianit.cityguidebe.dto.UserDto;
import com.arianit.cityguidebe.dto.request.BusTripReservationRequest;
import com.arianit.cityguidebe.entity.BusTripReservation;
import com.arianit.cityguidebe.exception.ResourceNotFoundException;
import com.arianit.cityguidebe.mapper.BusTripReservationMapper;
import com.arianit.cityguidebe.util.ReflectionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    UserDto currentUser = userService.getCurrentUser();

    public BusTripReservationDto create (BusTripReservationRequest request){
        BusTripReservation busTripReservation = busTripReservationMapper.toEntity(request);
        mapBusTripToBusTripReservation(request, busTripReservation);
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

    public  BusTripReservationDto update(Long id, Map<String, Object> fields) {
         BusTripReservation busTripReservationInDb = busTripReservationRepository
                .findById(id).orElseThrow(()-> new ResourceNotFoundException(
                        String.format("BusTripReservation with id %s not found", id)
                ));
        fields.forEach((key, value) ->{
            ReflectionUtil.setFieldValue(busTripReservationInDb, key, value);
        });
        return busTripReservationMapper.toDto(busTripReservationRepository.save(busTripReservationInDb));
    }

    public void mapBusTripToBusTripReservation(BusTripReservationRequest req, BusTripReservation busTripReservation){
        BusTripDto busTripDto = busTripService.getById(req.busTripId());
        busTripReservation.setBusTripId(busTripDto.getId());
    }



    public List<BusTripReservationDto> findBusTripReservationByUsername() {
        List<BusTripReservation> busTripReservations =
                busTripReservationRepository.findBusTripReservationByUsername(currentUser.getUsername());
        return busTripReservations.stream()
                .map(busTripReservationMapper::toDto)
                .collect(Collectors.toList());
    }
}

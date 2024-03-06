package com.arianit.cityguidebe.service;

import com.arianit.cityguidebe.dao.GastronomeRepository;
import com.arianit.cityguidebe.dao.ReservationRepository;
import com.arianit.cityguidebe.dto.ReservationDto;
import com.arianit.cityguidebe.dto.request.ReservationRequest;
import com.arianit.cityguidebe.entity.Gastronome;
import com.arianit.cityguidebe.entity.Reservation;
import com.arianit.cityguidebe.exception.ResourceNotFoundException;
import com.arianit.cityguidebe.mapper.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;
    private final GastronomeRepository gastronomeRepository;

    public ReservationDto create(ReservationRequest reservationRequest){
        Gastronome gastronomeInDb = gastronomeRepository.findById(reservationRequest.gastronomeId())
                .orElseThrow(()-> new ResourceNotFoundException(
                        String.format("Gastronome with %s id not found",reservationRequest.gastronomeId())
                ));
        Reservation reservation = reservationMapper.toEntity(reservationRequest);
        reservation.setGastronomeId(reservationRequest.gastronomeId());
        Reservation reservationInDb = reservationRepository.save(reservation);
        return reservationMapper.toDto(reservationInDb);
    }

    public ReservationDto getById(Long id){
        Reservation reservationInDb = reservationRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(
                        String.format("Gastronome with %s id not found",id)
                ));
        return reservationMapper.toDto(reservationInDb);
    }

    public List<ReservationDto> getAll(){
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream()
                .map(reservationMapper::toDto)
                .collect(Collectors.toList());
    }

    public void delete(Long id){
        Reservation reservationInDb = reservationRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException(
                        String.format("Gastronome with %s id not found",id)
                ));
        reservationRepository.deleteById(id);
    }
}

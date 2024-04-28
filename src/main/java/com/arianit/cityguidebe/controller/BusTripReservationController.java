package com.arianit.cityguidebe.controller;

import com.arianit.cityguidebe.dto.BusTripReservationDto;
import com.arianit.cityguidebe.dto.UserDto;
import com.arianit.cityguidebe.dto.request.BusTripReservationRequest;
import com.arianit.cityguidebe.dto.request.UpdateBusTripReservationRequest;
import com.arianit.cityguidebe.entity.BusTripReservation;
import com.arianit.cityguidebe.service.BusTripReservationService;
import com.arianit.cityguidebe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bustripreservations")
@RequiredArgsConstructor
public class BusTripReservationController {

    private final BusTripReservationService busTripReservationService;

    @PostMapping
    public ResponseEntity<BusTripReservationDto> createBusTripReservation(@RequestBody BusTripReservationRequest request) {
        BusTripReservationDto reservationDto = busTripReservationService.create(request);
        return new ResponseEntity<>(reservationDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BusTripReservationDto>> getAllBusTripReservations() {
        List<BusTripReservationDto> reservations = busTripReservationService.getAll();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusTripReservationDto> getBusTripReservationById(@PathVariable Long id) {
        BusTripReservationDto reservationDto = busTripReservationService.getById(id);
        return new ResponseEntity<>(reservationDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBusTripReservation(@PathVariable Long id) {
        busTripReservationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BusTripReservationDto> updateBusTripReservation(
            @PathVariable Long id, @RequestBody UpdateBusTripReservationRequest request) {
        BusTripReservationDto updatedReservation = busTripReservationService.update(id, request);
        return new ResponseEntity<>(updatedReservation, HttpStatus.OK);
    }

    @GetMapping("/by-username")
    public ResponseEntity<List<BusTripReservationDto>> findBusTripReservationByUsername() {
        List<BusTripReservationDto> reservations = busTripReservationService.findBusTripReservationByUsername();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

}

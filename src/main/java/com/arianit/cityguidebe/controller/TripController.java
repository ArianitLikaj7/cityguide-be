package com.arianit.cityguidebe.controller;

import com.arianit.cityguidebe.dto.TripDto;
import com.arianit.cityguidebe.dto.request.TripRequest;
import com.arianit.cityguidebe.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trips")
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;


    @PostMapping
    public ResponseEntity<TripDto> createAdvanceTrip(@RequestBody TripRequest tripRequest) {
        TripDto citiesWithGastronomies = tripService.createAdvanceTrip(tripRequest.getCityIds(), tripRequest.getTypeOfGastronomes());
        return new ResponseEntity<>(citiesWithGastronomies, HttpStatus.OK);
    }
}
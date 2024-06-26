package com.arianit.cityguidebe.controller;

import com.arianit.cityguidebe.dto.TripDto;
import com.arianit.cityguidebe.dto.request.TripRequest;
import com.arianit.cityguidebe.entity.TypeOfGastronome;
import com.arianit.cityguidebe.service.TripService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trips")
@RequiredArgsConstructor

public class TripController {

    private final TripService tripService;

    @PostMapping
    public ResponseEntity<TripDto> createAdvanceTrip(@RequestBody TripRequest tripRequest) {
        TripDto citiesWithGastronomies = tripService.create(
                tripRequest.getCityIds(), tripRequest.getTypeOfGastronomes(), tripRequest.getNumOfDays());
        return new ResponseEntity<>(citiesWithGastronomies, HttpStatus.OK);
    }
    @PostMapping("/generate")
    public ResponseEntity<TripDto> generateTrip(@RequestBody TripRequest tripRequest) {
        List<Long> cityIds = tripRequest.getCityIds();
        List<TypeOfGastronome> gastronomyTypes = tripRequest.getTypeOfGastronomes();
        int numOfDays = tripRequest.getNumOfDays();
        TripDto tripDto = tripService.generate(cityIds, gastronomyTypes, numOfDays);
        return new ResponseEntity<>(tripDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TripDto>> getAllTrips() {
        List<TripDto> trips = tripService.getAllTrips();
        return new ResponseEntity<>(trips, HttpStatus.OK);
    }
    @DeleteMapping("/{tripId}")
    public ResponseEntity<String> deleteTrip(@PathVariable Long tripId) {
        tripService.deleteTrip(tripId);
        return new ResponseEntity<>("Trip deleted successfully", HttpStatus.OK);
    }
}

package com.arianit.cityguidebe.controller;


import com.arianit.cityguidebe.dto.BusTripDto;
import com.arianit.cityguidebe.dto.request.BusTripRequest;
import com.arianit.cityguidebe.service.BusTripService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bustrips")
@RequiredArgsConstructor
public class BusTripController {

    private final BusTripService busTripService;

    @PostMapping
    public ResponseEntity<BusTripDto> createBusTrip(@RequestBody BusTripRequest busTripRequest) {
        BusTripDto createdBusTrip = busTripService.create(busTripRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBusTrip);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusTripDto> getBusTripById(@PathVariable Long id) {
        BusTripDto busTripDTO = busTripService.getById(id);
        return ResponseEntity.ok(busTripDTO);
    }

    @GetMapping
    public ResponseEntity<List<BusTripDto>> getAllBusTrips() {
        List<BusTripDto> busTripDTOList = busTripService.getAll();
        return ResponseEntity.ok(busTripDTOList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BusTripDto> updateBusTrip(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        BusTripDto updatedBusTrip = busTripService.update(id, fields);
        return ResponseEntity.ok(updatedBusTrip);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBusTrip(@PathVariable Long id) {
        busTripService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

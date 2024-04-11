package com.arianit.cityguidebe.controller;

import com.arianit.cityguidebe.dto.BusTripDto;
import com.arianit.cityguidebe.dto.request.BusTripRequest;
import com.arianit.cityguidebe.dto.request.PageRequest;
import com.arianit.cityguidebe.service.BusTripService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("bustrips")
@RequiredArgsConstructor
public class BusTripController {
    private final BusTripService busTripService;

    @PostMapping
    public ResponseEntity<BusTripDto> create(@RequestBody BusTripRequest request){
        return new ResponseEntity<>(busTripService.create(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BusTripDto>> getAll(){
        return new ResponseEntity<>(busTripService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<List<BusTripDto>> getAll(
            @RequestParam String startLocation,
            @RequestParam String destination){
        return new ResponseEntity<>(
                busTripService.getByStartStationAndDestination(startLocation,destination),
                HttpStatus.OK);
    }

    @GetMapping("/pageable")
    public Page<BusTripDto> getAllBusTripsPagable(
            @RequestParam String startLocation,
            @RequestParam String destination,
            PageRequest pageRequest){
        return busTripService.getByStartStationAndDestinationPageable(startLocation,destination,pageRequest);
    }
    @GetMapping("/{id}")
    public ResponseEntity<BusTripDto> getById(@PathVariable Long id){
        return new ResponseEntity<>(busTripService.getById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BusTripDto> update(
            @PathVariable Long id,
            @RequestBody Map<String,Object> fields) {
        BusTripDto updatedBusTrip = busTripService.update(id, fields);
        return new ResponseEntity<>(updatedBusTrip, HttpStatus.OK);
    }
}

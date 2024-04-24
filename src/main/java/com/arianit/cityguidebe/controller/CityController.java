package com.arianit.cityguidebe.controller;

import com.arianit.cityguidebe.dto.CityDto;
import com.arianit.cityguidebe.dto.request.CityRequest;
import com.arianit.cityguidebe.dto.request.PageRequest;
import com.arianit.cityguidebe.service.CityService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cities")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CityController {

    private final CityService cityService;

    @PostMapping
    public ResponseEntity<CityDto> create (@RequestBody @Valid CityRequest cityRequest) {
        return new ResponseEntity<>(cityService.create(cityRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityDto> getById(@PathVariable Long id){
         CityDto cityDto = cityService.getById(id);
        return new ResponseEntity<>(cityDto, HttpStatus.OK);
    }

    @GetMapping("/pagable")
    public Page<CityDto> getAllPagable(@Valid PageRequest pageRequest){
        return cityService.getAllPagable(pageRequest);
    }

    @GetMapping
    public ResponseEntity<List<CityDto>> getAll(){
        return new ResponseEntity<>(cityService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/getCityByPrefix")
    public CityDto getCitiesByPrefix(@RequestParam String prefix) {
        return cityService.findCitiesByCityPrefix(prefix);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CityDto> update(
            @PathVariable Long id,
            @RequestBody Map<String,Object> fields) {
        CityDto updatedCity = cityService.update(id, fields);
        return new ResponseEntity<>(updatedCity, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cityService.delete(id);
        return ResponseEntity.noContent().build();
    }


}

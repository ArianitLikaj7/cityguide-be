package com.arianit.cityguidebe.controller;

import com.arianit.cityguidebe.dto.StateDto;
import com.arianit.cityguidebe.dto.request.StateRequest;
import com.arianit.cityguidebe.entity.TypeOfGastronome;
import com.arianit.cityguidebe.service.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/states")
@RequiredArgsConstructor
public class StateController {

    private final StateService stateService;
    @PostMapping
    public ResponseEntity<StateDto> create(@RequestBody StateRequest request){
        return new ResponseEntity<>(stateService.create(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StateDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(stateService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<StateDto>>getAll(){
        return ResponseEntity.ok(stateService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<StateDto> update(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        return ResponseEntity.ok(stateService.update(id,fields));
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        stateService.deleteById(id);
        return "Deleted";
    }

}

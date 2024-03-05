package com.arianit.cityguidebe.controller;

import com.arianit.cityguidebe.dto.GastronomeDto;
import com.arianit.cityguidebe.dto.request.GastronomeRequest;
import com.arianit.cityguidebe.service.GastronomeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gastronomes")
public class GastronomeController {

    private final GastronomeService gastronomeService;

    public GastronomeController(GastronomeService gastronomeService) {
        this.gastronomeService = gastronomeService;
    }

    @PostMapping
    public ResponseEntity<GastronomeDto> createGastronome(@RequestBody GastronomeRequest gastronomeRequest) {
        GastronomeDto createdGastronome = gastronomeService.create(gastronomeRequest);
        return new ResponseEntity<>(createdGastronome, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GastronomeDto> getGastronomeById(@PathVariable Long id) {
        GastronomeDto gastronomeDto = gastronomeService.getById(id);
        return ResponseEntity.ok(gastronomeDto);
    }

    @GetMapping
    public ResponseEntity<List<GastronomeDto>> getAllGastronomes() {
        List<GastronomeDto> gastronomeDtos = gastronomeService.getAll();
        return ResponseEntity.ok(gastronomeDtos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGastronome(@PathVariable Long id) {
        gastronomeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

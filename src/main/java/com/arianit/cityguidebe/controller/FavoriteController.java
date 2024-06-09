package com.arianit.cityguidebe.controller;

import com.arianit.cityguidebe.dto.FavoriteDto;
import com.arianit.cityguidebe.dto.GastronomeDto;
import com.arianit.cityguidebe.dto.request.FavoriteRequest;
import com.arianit.cityguidebe.service.FavoriteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @PostMapping
    public ResponseEntity<FavoriteDto> create(@RequestBody @Valid FavoriteRequest favoriteRequest) {
        FavoriteDto createdFavorite = favoriteService.create(favoriteRequest);
        return new ResponseEntity<>(createdFavorite, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FavoriteDto> getById(@PathVariable Long id) {
        FavoriteDto favoriteDto = favoriteService.getById(id);
        return new ResponseEntity<>(favoriteDto, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<GastronomeDto>> getUserFavoriteGastronomies(@RequestParam String username) {
        List<GastronomeDto> favoriteGastronomies = favoriteService.getUserFavoriteGastronomies(username);
        return new ResponseEntity<>(favoriteGastronomies, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<FavoriteDto>> getAll() {
        List<FavoriteDto> favorites = favoriteService.getAll();
        return new ResponseEntity<>(favorites, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        favoriteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/byGastronomeId{id}")
    public ResponseEntity<Void> deleteByGastronomeId(@PathVariable Long id) {
        favoriteService.deleteFavoritesByGasronomeId(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/gastronomes/ids")
    public List<Long> getGastronomeIdsByUser(@RequestParam String username) {
        return favoriteService.findGastronomeIdByNameOfUser(username);
    }
}

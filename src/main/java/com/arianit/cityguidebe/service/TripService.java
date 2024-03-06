package com.arianit.cityguidebe.service;

import com.arianit.cityguidebe.dao.CityRepository;
import com.arianit.cityguidebe.dao.TripRepository;
import com.arianit.cityguidebe.dto.CityDto;
import com.arianit.cityguidebe.dto.GastronomeDto;
import com.arianit.cityguidebe.dto.TripDto;
import com.arianit.cityguidebe.entity.City;
import com.arianit.cityguidebe.entity.Trip;
import com.arianit.cityguidebe.entity.TypeOfGastronome;
import com.arianit.cityguidebe.mapper.CityMapper;
import com.arianit.cityguidebe.mapper.TripMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TripService {
    private final TripRepository tripRepository;
    private final TripMapper tripMapper;
    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    public TripDto createAdvanceTrip(List<Long> cityIds, List<TypeOfGastronome> gastronomyTypes) {
        List<City> cities = cityRepository.findAllById(cityIds);
        Trip trip = new Trip();
        trip.setCityIds(cityIds);
        trip.setTypesOfGastronome(gastronomyTypes);

        trip = tripRepository.save(trip);

        List<CityDto> cityDtos = cities.stream()
                .map(city -> {
                    CityDto cityDto = cityMapper.toDto(city);
                    List<GastronomeDto> gastronomeDtos = city.getGastronomes().stream()
                            .filter(g -> gastronomyTypes.contains(g.getTypeOfGastronome()) && g.isSponsored())
                            .map(g -> GastronomeDto.builder().cityId(g.getId()).nameOfGastronome(g.getNameOfGastronome()).typeOfGastronome(String.valueOf(g.getTypeOfGastronome())).build())
                            .collect(Collectors.toList());
                    cityDto.setGastronomeDtos(gastronomeDtos);
                    return cityDto;
                })
                .collect(Collectors.toList());

        TripDto tripDto = TripDto.builder()
                .id(trip.getId())
                .cityIds(cityIds)
                .typesOfGastronome(gastronomyTypes)
                .cityDtos(cityDtos)
                .build();

        return tripDto;
    }



}

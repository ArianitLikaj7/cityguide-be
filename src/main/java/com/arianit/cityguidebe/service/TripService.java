package com.arianit.cityguidebe.service;

import com.arianit.cityguidebe.dao.CityRepository;
import com.arianit.cityguidebe.dao.TripRepository;
import com.arianit.cityguidebe.dto.CityDto;
import com.arianit.cityguidebe.dto.GastronomeDto;
import com.arianit.cityguidebe.dto.TripDto;
import com.arianit.cityguidebe.entity.City;
import com.arianit.cityguidebe.entity.Gastronome;
import com.arianit.cityguidebe.entity.Trip;
import com.arianit.cityguidebe.entity.TypeOfGastronome;
import com.arianit.cityguidebe.mapper.CityMapper;
import com.arianit.cityguidebe.mapper.GastronomeMapper;
import com.arianit.cityguidebe.mapper.TripMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TripService {
    private final TripRepository tripRepository;
    private final TripMapper tripMapper;
    private final CityRepository cityRepository;
    private final CityMapper cityMapper;
    private final GastronomeMapper gastronomeMapper;


    public TripDto createAdvanceTrip1(List<Long> cityIds, List<TypeOfGastronome> gastronomyTypes, int numOfDays) {
        List<City> cities = cityRepository.findAllById(cityIds);
        Trip trip = new Trip();
        trip.setCityIds(cityIds);
        trip.setTypesOfGastronome(gastronomyTypes);
        trip = tripRepository.save(trip);

        List<CityDto> cityDtos = cities.stream()
                .map(city -> {
                    List<Gastronome> sponsoredGastronomies = city.getGastronomes().stream()
                            .filter(g -> gastronomyTypes.contains(g.getTypeOfGastronome()) && g.isSponsored())
                            .collect(Collectors.toList());
                    List<Gastronome> nonSponsoredGastronomies = city.getGastronomes().stream()
                            .filter(g -> gastronomyTypes.contains(g.getTypeOfGastronome()) && !g.isSponsored())
                            .collect(Collectors.toList());
                    List<Gastronome> combinedGastronomies = new ArrayList<>();
                    Collections.shuffle(sponsoredGastronomies);
                    Collections.shuffle(nonSponsoredGastronomies);
                    combinedGastronomies.addAll(sponsoredGastronomies);
                    combinedGastronomies.addAll(nonSponsoredGastronomies);
                    city.setGastronomes(combinedGastronomies);
                    return cityMapper.toDto(city);
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
    public TripDto createAdvanceTrip(List<Long> cityIds, List<TypeOfGastronome> gastronomyTypes, int numOfDays) {
        List<City> cities = cityRepository.findAllById(cityIds);
        Trip trip = new Trip();
        trip.setCityIds(cityIds);
        trip.setTypesOfGastronome(gastronomyTypes);
        trip = tripRepository.save(trip);
        List<CityDto> cityDtos = cities.stream()
                .map(city -> {
                    List<Gastronome> filteredGastronomies = city.getGastronomes().stream()
                            .filter(g -> gastronomyTypes.contains(g.getTypeOfGastronome()) && g.isSponsored())
                            .collect(Collectors.toList());
                    filteredGastronomies.addAll(city.getGastronomes().stream()
                            .filter(g -> gastronomyTypes.contains(g.getTypeOfGastronome()) && !g.isSponsored())
                            .collect(Collectors.toList()));
                    city.setGastronomes(filteredGastronomies);
                    return cityMapper.toDto(city);
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
    public TripDto generateAdvanceTrip2(List<Long> cityIds, List<TypeOfGastronome> gastronomyTypes, int numOfDays) {
        List<City> cities = cityRepository.findAllById(cityIds);
        List<CityDto> cityDtos = cities.stream()
                .map(city -> {
                    List<Gastronome> sponsoredGastronomies = city.getGastronomes().stream()
                            .filter(g -> gastronomyTypes.contains(g.getTypeOfGastronome()) && g.isSponsored())
                            .collect(Collectors.toList());
                    List<Gastronome> nonSponsoredGastronomies = city.getGastronomes().stream()
                            .filter(g -> gastronomyTypes.contains(g.getTypeOfGastronome()) && !g.isSponsored())
                            .collect(Collectors.toList());
                    List<Gastronome> combinedGastronomies = new ArrayList<>();
                    Collections.shuffle(sponsoredGastronomies);
                    Collections.shuffle(nonSponsoredGastronomies);
                    combinedGastronomies.addAll(sponsoredGastronomies);
                    combinedGastronomies.addAll(nonSponsoredGastronomies);
                    city.setGastronomes(combinedGastronomies);
                    return cityMapper.toDto(city);
                })
                .collect(Collectors.toList());

        TripDto tripDto = TripDto.builder()
                .cityIds(cityIds)
                .typesOfGastronome(gastronomyTypes)
                .cityDtos(cityDtos)
                .build();

        return tripDto;
    }
    public TripDto generateAdvanceTrip(List<Long> cityIds,
                                       List<TypeOfGastronome> gastronomyTypes, int numOfDays) {
        List<City> cities = cityRepository.findAllById(cityIds);
        List<CityDto> cityDtos = cities.stream()
                .map(city -> {
                    List<Gastronome> filteredGastronomies = city.getGastronomes().stream()
                            .filter(g -> gastronomyTypes.contains(g.getTypeOfGastronome()) && g.isSponsored())
                            .collect(Collectors.toList());
                    filteredGastronomies.addAll(city.getGastronomes().stream()
                            .filter(g -> gastronomyTypes.contains(g.getTypeOfGastronome()) && !g.isSponsored())
                            .collect(Collectors.toList()));
                    city.setGastronomes(filteredGastronomies);
                    return cityMapper.toDto(city);
                })
                .collect(Collectors.toList());

        TripDto tripDto = TripDto.builder()
                .cityIds(cityIds)
                .typesOfGastronome(gastronomyTypes)
                .cityDtos(cityDtos)
                .build();
        return tripDto;
    }

    public List<TripDto> getAllTrips() {
        List<Trip> trips = tripRepository.findAll();
        List<TripDto> tripDtos = new ArrayList<>();

        for (Trip trip : trips) {
            List<CityDto> cityDtos = new ArrayList<>();
            for (Long cityId : trip.getCityIds()) {
                City city = cityRepository.findById(cityId)
                        .orElseThrow(() -> new EntityNotFoundException("City not found with ID: " + cityId));

                CityDto cityDto = cityMapper.toDto(city);

                List<Gastronome> gastronomies = city.getGastronomes().stream()
                        .filter(g -> trip.getTypesOfGastronome().contains(g.getTypeOfGastronome()))
                        .collect(Collectors.toList());
                List<GastronomeDto> gastronomeDtos = gastronomies.stream()
                        .map(gastronomeMapper::toDto)
                        .collect(Collectors.toList());
                cityDto.setGastronomeDtos(gastronomeDtos);

                cityDtos.add(cityDto);
            }

            TripDto tripDto = new TripDto();
            tripDto.setId(trip.getId());
            tripDto.setNumOfDays(trip.getNumOfDays());
            tripDto.setCityIds(trip.getCityIds());
            tripDto.setTypesOfGastronome(trip.getTypesOfGastronome());
            tripDto.setCityDtos(cityDtos);

            tripDtos.add(tripDto);
        }

        return tripDtos;
    }

    public TripDto getTripById(Long tripId) {
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new EntityNotFoundException("Trip not found with id " + tripId));

        List<CityDto> cityDtos = new ArrayList<>();
        for (Long cityId : trip.getCityIds()) {
            City city = cityRepository.findById(cityId)
                    .orElseThrow(() -> new EntityNotFoundException("City not found with ID: " + cityId));

            CityDto cityDto = cityMapper.toDto(city);

            List<Gastronome> gastronomies = city.getGastronomes().stream()
                    .filter(g -> trip.getTypesOfGastronome().contains(g.getTypeOfGastronome()))
                    .collect(Collectors.toList());
            List<GastronomeDto> gastronomeDtos = gastronomies.stream()
                    .map(gastronomeMapper::toDto)
                    .collect(Collectors.toList());
            cityDto.setGastronomeDtos(gastronomeDtos);

            cityDtos.add(cityDto);
        }
        TripDto tripDto = new TripDto();
        tripDto.setId(trip.getId());
        tripDto.setNumOfDays(trip.getNumOfDays());
        tripDto.setCityIds(trip.getCityIds());
        tripDto.setTypesOfGastronome(trip.getTypesOfGastronome());
        tripDto.setCityDtos(cityDtos);
        return tripDto;
    }

    public void deleteTrip(Long tripId) {
        if (!tripRepository.existsById(tripId)) {
            throw new EntityNotFoundException("Trip not found with id " + tripId);
        }
        tripRepository.deleteById(tripId);
    }
}

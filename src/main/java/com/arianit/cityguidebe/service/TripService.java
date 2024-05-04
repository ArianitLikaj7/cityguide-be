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

    public TripDto create(List<Long> cityIds, List<TypeOfGastronome> gastronomyTypes, int numOfDays) {
        List<City> cities = cityRepository.findAllById(cityIds);
        Trip trip = new Trip();
        trip.setCityIds(cityIds);
        trip.setNumOfDays(numOfDays);
        trip.setTypesOfGastronome(gastronomyTypes);
        trip = tripRepository.save(trip);

        List<List<CityDto>> cityDtosPerDay = new ArrayList<>();
        for (int day = 0; day < numOfDays; day++) {
            final int currentDay = day;
            List<CityDto> cityDtos = cities.stream()
                    .filter(city -> cityIds.get(currentDay % cityIds.size()).equals(city.getId()))  // Ensures cities rotate over days if fewer cities than days
                    .map(city -> {
                        List<Gastronome> filteredGastronomies = city.getGastronomes().stream()
                                .filter(g -> gastronomyTypes.contains(g.getTypeOfGastronome()) && g.isSponsored())
                                .collect(Collectors.toList());
                        filteredGastronomies.addAll(city.getGastronomes().stream()
                                .filter(g -> gastronomyTypes.contains(g.getTypeOfGastronome()) && !g.isSponsored())
                                .collect(Collectors.toList()));
                        city.setGastronomes(filteredGastronomies);
                        return CityDto.builder()
                                .day(currentDay + 1)
                                .nameOfCity(city.getNameOfCity())
                                .culturalHeritage(city.getCulturalHeritage())
                                .cityPrefix(city.getCityPrefix())
                                .taxiPhoneNumber(city.getTaxiPhoneNumber())
                                .gastronomeDtos(filteredGastronomies.stream().map(gastronomeMapper::toDto).collect(Collectors.toList()))
                                .build();
                    })
                    .collect(Collectors.toList());
            cityDtosPerDay.add(cityDtos);
        }

        TripDto tripDto = TripDto.builder()
                .id(trip.getId())
                .numOfDays(numOfDays)
                .cityIds(cityIds)
                .typesOfGastronome(gastronomyTypes)
                .cityDtosPerDay(cityDtosPerDay)
                .build();
        return tripDto;
    }

    public TripDto generate(List<Long> cityIds, List<TypeOfGastronome> gastronomyTypes, int numOfDays) {
        List<City> cities = cityRepository.findAllById(cityIds);
        Trip trip = new Trip(); // Used only as a model, not saved
        trip.setCityIds(cityIds);
        trip.setNumOfDays(numOfDays);
        trip.setTypesOfGastronome(gastronomyTypes);

        List<List<CityDto>> cityDtosPerDay = new ArrayList<>();
        for (int day = 0; day < numOfDays; day++) {
            final int currentDay = day;
            List<CityDto> cityDtos = cities.stream()
                    .filter(city -> cityIds.get(currentDay % cityIds.size()).equals(city.getId()))  // Rotates cities over days if fewer cities than days
                    .map(city -> {
                        List<Gastronome> filteredGastronomies = city.getGastronomes().stream()
                                .filter(g -> gastronomyTypes.contains(g.getTypeOfGastronome()) && g.isSponsored())
                                .collect(Collectors.toList());
                        filteredGastronomies.addAll(city.getGastronomes().stream()
                                .filter(g -> gastronomyTypes.contains(g.getTypeOfGastronome()) && !g.isSponsored())
                                .collect(Collectors.toList()));
                        return CityDto.builder()
                                .day(currentDay + 1)
                                .nameOfCity(city.getNameOfCity())
                                .culturalHeritage(city.getCulturalHeritage())
                                .cityPrefix(city.getCityPrefix())
                                .taxiPhoneNumber(city.getTaxiPhoneNumber())
                                .gastronomeDtos(filteredGastronomies.stream().map(gastronomeMapper::toDto).collect(Collectors.toList()))
                                .build();
                    })
                    .collect(Collectors.toList());
            cityDtosPerDay.add(cityDtos);
        }

        TripDto tripDto = TripDto.builder()
                .numOfDays(numOfDays)
                .cityIds(cityIds)
                .typesOfGastronome(gastronomyTypes)
                .cityDtosPerDay(cityDtosPerDay)
                .build();

        return tripDto;
    }

    public List<TripDto> getAllTrips() {
        List<Trip> trips = tripRepository.findAll();
        return trips.stream().map(this::convertTripToDto).collect(Collectors.toList());
    }

    private TripDto convertTripToDto(Trip trip) {
        List<City> cities = cityRepository.findAllById(trip.getCityIds());
        List<List<CityDto>> cityDtosPerDay = new ArrayList<>();
        int dayCount = 1;
        for (City city : cities) {
            List<Gastronome> gastronomies = city.getGastronomes().stream()
                    .filter(g -> trip.getTypesOfGastronome().contains(g.getTypeOfGastronome()))
                    .collect(Collectors.toList());
            List<GastronomeDto> gastronomeDtos = gastronomies.stream()
                    .map(gastronomeMapper::toDto)
                    .collect(Collectors.toList());

            CityDto cityDto = CityDto.builder()
                    .day(dayCount++)
                    .nameOfCity(city.getNameOfCity())
                    .culturalHeritage(city.getCulturalHeritage())
                    .cityPrefix(city.getCityPrefix())
                    .taxiPhoneNumber(city.getTaxiPhoneNumber())
                    .gastronomeDtos(gastronomeDtos)
                    .build();
            cityDtosPerDay.add(Collections.singletonList(cityDto));
        }

        return TripDto.builder()
                .id(trip.getId())
                .numOfDays(trip.getNumOfDays())
                .cityIds(trip.getCityIds())
                .typesOfGastronome(trip.getTypesOfGastronome())
                .cityDtosPerDay(cityDtosPerDay)
                .build();
    }


    public void deleteTrip(Long tripId) {
        if (!tripRepository.existsById(tripId)) {
            throw new EntityNotFoundException("Trip not found with id " + tripId);
        }
        tripRepository.deleteById(tripId);
    }
}

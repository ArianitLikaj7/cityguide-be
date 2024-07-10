package com.arianit.cityguidebe.dao;

import com.arianit.cityguidebe.dto.CityDto;
import com.arianit.cityguidebe.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {
    City findCitiesByCityPrefix(String cityPrefix);

    @Query("SELECT new com.arianit.cityguidebe.dto.CityDto (" +
            "c.nameOfCity) FROM City c WHERE c.nameOfCity = ?1")
    Optional<CityDto> findById(String id);

}

package com.arianit.cityguidebe.dao;

import com.arianit.cityguidebe.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {
    List<City> findCitiesByCityPrefix(String cityPrefix);
}

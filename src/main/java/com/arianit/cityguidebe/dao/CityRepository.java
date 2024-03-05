package com.arianit.cityguidebe.dao;

import com.arianit.cityguidebe.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}

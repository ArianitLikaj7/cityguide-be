package com.arianit.cityguidebe.dao;

import com.arianit.cityguidebe.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, Long> {
}

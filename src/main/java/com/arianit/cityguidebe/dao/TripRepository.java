package com.arianit.cityguidebe.dao;

import com.arianit.cityguidebe.dto.TripDto;
import com.arianit.cityguidebe.entity.Trip;
import com.arianit.cityguidebe.entity.TypeOfGastronome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {
}

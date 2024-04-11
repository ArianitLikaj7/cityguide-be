package com.arianit.cityguidebe.dao;


import com.arianit.cityguidebe.entity.BusTrip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusTripRepository extends JpaRepository<BusTrip, Long> {
    List<BusTrip> findByStartStationAndDestination(String startStation, String destination);
    Page<BusTrip> findByStartStationAndDestination(String startStation, String destination, Pageable pagable);
}

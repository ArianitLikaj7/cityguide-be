package com.arianit.cityguidebe.dao;

import com.arianit.cityguidebe.entity.BusTrip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusTripRepository extends JpaRepository<BusTrip, Long> {
}

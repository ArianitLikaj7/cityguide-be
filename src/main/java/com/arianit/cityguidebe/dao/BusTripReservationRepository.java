package com.arianit.cityguidebe.dao;

import com.arianit.cityguidebe.entity.BusTripReservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusTripReservationRepository extends JpaRepository<BusTripReservation, Long> {
    List<BusTripReservation> findBusTripReservationByUsername(String username);
}

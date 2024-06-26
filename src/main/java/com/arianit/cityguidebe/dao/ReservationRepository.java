package com.arianit.cityguidebe.dao;

import com.arianit.cityguidebe.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByGastronomeId(Long gastronomeId);
}

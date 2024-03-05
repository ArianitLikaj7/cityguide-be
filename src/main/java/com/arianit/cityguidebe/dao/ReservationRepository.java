package com.arianit.cityguidebe.dao;

import com.arianit.cityguidebe.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}

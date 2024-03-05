package com.arianit.cityguidebe.dao;

import com.arianit.cityguidebe.entity.Gastronome;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GastronomeRepository extends JpaRepository<Gastronome, Long> {
}

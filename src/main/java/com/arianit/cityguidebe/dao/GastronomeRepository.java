package com.arianit.cityguidebe.dao;

import com.arianit.cityguidebe.entity.Gastronome;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GastronomeRepository extends JpaRepository<Gastronome, Long> {
    List<Gastronome> findByCityId(Long cityId);
    List<Gastronome> findByUserId(Long userId);

}

package com.arianit.cityguidebe.dao;

import com.arianit.cityguidebe.entity.Favorite;
import com.arianit.cityguidebe.entity.Gastronome;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    List<Long> findGastronomeIdByNameOfUser(String username);

    List<Favorite> findByNameOfUser(String username);
}

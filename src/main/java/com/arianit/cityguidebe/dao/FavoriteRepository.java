package com.arianit.cityguidebe.dao;

import com.arianit.cityguidebe.entity.Favorite;
import com.arianit.cityguidebe.entity.Gastronome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    @Query("select f.gastronome.id from Favorite f where f.user.username = :username")
    List<Long> findGastronomeIdByNameOfUser(@Param("username") String username);


    List<Favorite> findByNameOfUser(String username);

}

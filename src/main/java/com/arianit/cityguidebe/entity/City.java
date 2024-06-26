package com.arianit.cityguidebe.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cities")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class City extends BaseEntity {

    @Column(nullable = false)
    private String nameOfCity;

    @Column(nullable = false)
    private String culturalHeritage;

    @Column(
            nullable = false, unique = true)
    private String cityPrefix;

    @Column(name = "taxi_phone_numbers")
    private String [] taxiPhoneNumber;

    @OneToMany(mappedBy = "city")
    @JsonManagedReference
    private List<Gastronome> gastronomes;




}
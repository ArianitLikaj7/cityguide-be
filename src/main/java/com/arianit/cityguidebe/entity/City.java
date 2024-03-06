package com.arianit.cityguidebe.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

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
            nullable = false,
            unique = true
    )
    private String cityPrefix;

    @OneToMany(mappedBy = "city")
    @JsonManagedReference
    private List<Gastronome> gastronomes;

}
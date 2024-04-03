package com.arianit.cityguidebe.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "states")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class State extends BaseEntity{

    @Column(nullable = false, name = "name_of_state", unique = true)
    private String nameOfState;

    @OneToMany(mappedBy = "state")
    @JsonManagedReference
    private List<City> cities;
}

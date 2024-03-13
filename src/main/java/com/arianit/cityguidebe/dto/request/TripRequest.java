package com.arianit.cityguidebe.dto.request;

import com.arianit.cityguidebe.entity.TypeOfGastronome;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TripRequest {
    @NotBlank
    private List<TypeOfGastronome> typeOfGastronomes;
    @NotBlank
    private List<Long> cityIds;
    private int numOfDays;
}
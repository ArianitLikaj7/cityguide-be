package com.arianit.cityguidebe.dto.request;

import com.arianit.cityguidebe.entity.TypeOfGastronome;

import java.util.List;

public record TripRequest(
        List<TypeOfGastronome> typeOfGastronomes,
        List<Long> cityIds
) {
}

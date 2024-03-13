package com.arianit.cityguidebe.dto.request;

import com.arianit.cityguidebe.entity.TypeOfGastronome;

import java.util.List;

public record GastronomeRequest(
        Long cityId,
        String nameOfGastronome,
        String schedule,
        Double longitude,
        Double latitude,
        boolean sponsored,
        TypeOfGastronome typeOfGastronome,
        List<String> attachments,
        String description,
        Double price,
        String phoneNumber
) {
}

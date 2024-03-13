package com.arianit.cityguidebe.dto.request;

import com.arianit.cityguidebe.entity.TypeOfGastronome;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record GastronomeRequest(
        @NotBlank Long cityId,
        String nameOfGastronome,
        String schedule,
        @NotBlank Double longitude,
        @NotBlank Double latitude,
        boolean sponsored,
        TypeOfGastronome typeOfGastronome,
        List<String> attachments,
        Integer rating,
        String description,
        Double price,
        String phoneNumber
) {
}

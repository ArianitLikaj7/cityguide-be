package com.arianit.cityguidebe.dto;

import lombok.*;

import java.util.List;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GastronomeDto extends BaseDto {
    private Long cityId;
    private String nameOfGastronome;
    private String schedule;
    private Double longitude;
    private Double latitude;
    private String typeOfGastronome;
    private boolean sponsored;
    private List<String> attachments;
}

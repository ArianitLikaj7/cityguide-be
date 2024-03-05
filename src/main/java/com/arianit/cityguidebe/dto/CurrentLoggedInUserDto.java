package com.arianit.cityguidebe.dto;

import lombok.Builder;

@Builder
public record CurrentLoggedInUserDto(
        Long userId,
        String firstName,
        String lastName,
        String email,
        String role) {
}

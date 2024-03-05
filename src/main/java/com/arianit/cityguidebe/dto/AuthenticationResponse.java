package com.arianit.cityguidebe.dto;


import com.arianit.cityguidebe.entity.Role;

public record AuthenticationResponse(String token, String refreshToken, Role role) {
}

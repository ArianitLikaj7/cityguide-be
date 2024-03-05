package com.arianit.cityguidebe.dto.request;

import com.arianit.cityguidebe.entity.Role;

public record UserRequest(
        String firstName,
        String lastName,
        String username,
        String password,
        Role role
) {
}

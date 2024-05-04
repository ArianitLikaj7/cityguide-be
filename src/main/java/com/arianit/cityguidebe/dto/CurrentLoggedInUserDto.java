package com.arianit.cityguidebe.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CurrentLoggedInUserDto{
        private Long userId;
        private String firstName;
        private String lastName;
        private String email;
        private String role;
        private List<Long> favoriteGastronomeIds;
}

package com.arianit.cityguidebe.service;

import com.arianit.cityguidebe.config.JwtService;
import com.arianit.cityguidebe.dto.AuthenticationResponse;
import com.arianit.cityguidebe.dto.CurrentLoggedInUserDto;
import com.arianit.cityguidebe.dto.request.AuthenticationRequest;
import com.arianit.cityguidebe.dto.request.RefreshTokenRequest;
import com.arianit.cityguidebe.entity.User;
import com.arianit.cityguidebe.exception.TokenRefreshException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final CustomUserDetailService customUserDetailService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public final FavoriteService favoriteService;

    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );
        var user = customUserDetailService.loadUserByUsername(request.username());
        return new AuthenticationResponse(jwtService.generateToken(user),
                jwtService.generateRefreshToken(user),user.getRole());
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest request){
        String requestRefreshToken = request.token();

        User user = customUserDetailService.
                loadUserByUsername(jwtService.extractUsername(requestRefreshToken));

        if (!jwtService.isTokenValid(requestRefreshToken, user)){
            throw new TokenRefreshException("Refresh token was expired. Please make a new sign-in request");
        }

        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token, requestRefreshToken,user.getRole());
    }


    public CurrentLoggedInUserDto getLoggedInUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User loggedUser = (User) authentication.getPrincipal();
        List<Long> favoriteGastronomeIds = favoriteService.findGastronomeIdByNameOfUser(loggedUser.getUsername());


        return CurrentLoggedInUserDto.builder()
                .userId(loggedUser.getId())
                .firstName(loggedUser.getFirstName())
                .lastName(loggedUser.getLastName())
                .role(loggedUser.getRole().name())
                .email(loggedUser.getUsername())
                .favoriteGastronomeIds(favoriteGastronomeIds)
                .build();
    }
}

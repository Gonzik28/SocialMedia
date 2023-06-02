package com.gonzik28.SocialMedia.dto.utils;

import com.gonzik28.SocialMedia.dto.RequestAuthenticationDto;
import com.gonzik28.SocialMedia.dto.ResponseAuthenticationDto;
import com.gonzik28.SocialMedia.entity.AuthenticationEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthenticationUtils {

    public static ResponseAuthenticationDto authenticationEntityToResponse(AuthenticationEntity authenticationEntity) {
        ResponseAuthenticationDto responseAuthenticationDto = new ResponseAuthenticationDto();
        responseAuthenticationDto.setEmail(authenticationEntity.getEmail());
        responseAuthenticationDto.setUserName(authenticationEntity.getUserName());
        return responseAuthenticationDto;
    }

    public static AuthenticationEntity authenticationRequestToEntity(RequestAuthenticationDto requestAuthenticationDto,
                                                                     PasswordEncoder encoder) {
        AuthenticationEntity authenticationEntity = new AuthenticationEntity();
        authenticationEntity.setEmail(requestAuthenticationDto.getEmail());
        String encodedPassword = encoder.encode(requestAuthenticationDto.getPassword());
        authenticationEntity.setPassword(encodedPassword);
        authenticationEntity.setUserName(requestAuthenticationDto.getUserName());
        return authenticationEntity;
    }
}

package com.gonzik28.SocialMedia.service;

import com.gonzik28.SocialMedia.dto.RequestAuthenticationDto;
import com.gonzik28.SocialMedia.dto.ResponseAuthenticationDto;
import com.gonzik28.SocialMedia.dto.utils.AuthenticationUtils;
import com.gonzik28.SocialMedia.entity.AuthenticationEntity;
import com.gonzik28.SocialMedia.repository.AuthenticationRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
public class AuthenticationService {
    private final AuthenticationRepository authenticationRepository;

    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(AuthenticationRepository authenticationRepository, PasswordEncoder passwordEncoder) {
        this.authenticationRepository = authenticationRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseAuthenticationDto findByUserName(String userName) {
        if (authenticationRepository.findByUserName(userName).isPresent()) {
            AuthenticationEntity authenticationEntity = authenticationRepository.findByUserName(userName).get();
            return AuthenticationUtils.authenticationEntityToResponse(authenticationEntity);
        } else {
            return null;
        }
    }

    public ResponseAuthenticationDto findByUserNamePassword(String userName, String password) {
        Optional<AuthenticationEntity> authenticationEntityOptional = authenticationRepository.findByUserName(userName);
        if (authenticationEntityOptional.isPresent()) {
            AuthenticationEntity authenticationEntity = authenticationEntityOptional.get();
            String encodedPassword = authenticationEntity.getPassword();
            if (isPasswordMatches(encodedPassword, password)) {
                return AuthenticationUtils.authenticationEntityToResponse(authenticationEntity);
            }
        }
        return null;
    }

    public ResponseAuthenticationDto findByEmailPassword(String email, String password) {
        Optional<AuthenticationEntity> authenticationEntityOptional = authenticationRepository.findByEmail(email);
        if (authenticationEntityOptional.isPresent()) {
            AuthenticationEntity authenticationEntity = authenticationEntityOptional.get();
            String encodedPassword = authenticationEntity.getPassword();
            if (isPasswordMatches(encodedPassword, password)) {
                return AuthenticationUtils.authenticationEntityToResponse(authenticationEntity);
            }
        }
        return null;
    }

    public ResponseAuthenticationDto create(RequestAuthenticationDto requestAuthenticationDto) {
        String userName = requestAuthenticationDto.getUserName();
        if (!authenticationRepository.findByUserName(userName).isPresent()) {
            AuthenticationEntity authenticationEntity =
                    AuthenticationUtils.authenticationRequestToEntity(requestAuthenticationDto, passwordEncoder);
            authenticationEntity = authenticationRepository.save(authenticationEntity);
            return AuthenticationUtils.authenticationEntityToResponse(authenticationEntity);
        } else {
            return update(requestAuthenticationDto);
        }
    }

    public ResponseAuthenticationDto update(RequestAuthenticationDto requestAuthenticationDto) {
        String userName = requestAuthenticationDto.getUserName();
        if (authenticationRepository.findByUserName(userName).isPresent()) {
            AuthenticationEntity authenticationEntity = authenticationRepository.findByUserName(userName).get();
            String encodedPassword = passwordEncoder.encode(requestAuthenticationDto.getPassword());
            authenticationEntity.setPassword(encodedPassword);
            authenticationEntity.setEmail(requestAuthenticationDto.getEmail());
            authenticationEntity = authenticationRepository.save(authenticationEntity);
            return AuthenticationUtils.authenticationEntityToResponse(authenticationEntity);
        } else {
            return create(requestAuthenticationDto);
        }
    }

    public void delete(String userName) {
        authenticationRepository.deleteById(userName);
    }

    private boolean isPasswordMatches(String encodedPassword, String userInputPassword) {
        return passwordEncoder.matches(userInputPassword, encodedPassword);
    }

    public String getJWTToken(String username) {
        final int TIME = 600000;

        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TIME))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}

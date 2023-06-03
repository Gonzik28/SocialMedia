package com.gonzik28.SocialMedia.controller;

import com.gonzik28.SocialMedia.config.Right;
import com.gonzik28.SocialMedia.dto.RequestAuthenticationDto;
import com.gonzik28.SocialMedia.dto.RequestRightDto;
import com.gonzik28.SocialMedia.dto.ResponseAuthenticationDto;
import com.gonzik28.SocialMedia.service.AuthenticationService;
import com.gonzik28.SocialMedia.service.AccountService;
import com.gonzik28.SocialMedia.service.RightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authentication")
@Tag(name = "Аудентификация пользователя", description = "Создан для работы с логином и паролем.")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final AccountService accountService;
    private final RightService rightService;

    public AuthenticationController(AuthenticationService authenticationService, AccountService accountService,
                                    RightService rightService) {
        this.authenticationService = authenticationService;
        this.accountService = accountService;
        this.rightService = rightService;
    }

    @GetMapping(value = "/{userName}")
    @Operation(summary = "Получение данных аудентификация пользователя по id в данном случае userName уникален")
    public ResponseEntity<ResponseAuthenticationDto> findByUserName(@PathVariable
                                                                    @Parameter(description = "Имя пользователя")
                                                                    String userName) {
        if (authenticationService.findByUserName(userName) != null) {
            ResponseAuthenticationDto authenticationDto = authenticationService.findByUserName(userName);
            return ResponseEntity.ok(authenticationDto);
        } else {
            return (ResponseEntity<ResponseAuthenticationDto>) ResponseEntity.notFound();
        }
    }

    @GetMapping(value = "/")
    @Operation(summary = "Поиск данных аудентификация пользователя по логину и паролю")
    public ResponseEntity<ResponseAuthenticationDto> findByLoginPassword(@RequestParam
                                                                         @Parameter(description =
                                                                                 "Имя пользователя или email")
                                                                         String login,
                                                                         @RequestParam
                                                                         @Parameter(description = "пароль")
                                                                         String password) {
        if (authenticationService.findByEmailPassword(login, password) != null) {
            return ResponseEntity.ok(authenticationService.findByEmailPassword(login, password));
        } else if (authenticationService.findByUserNamePassword(login, password) != null) {
            return ResponseEntity.ok(authenticationService.findByUserNamePassword(login, password));
        } else {
            return (ResponseEntity<ResponseAuthenticationDto>) ResponseEntity.notFound();
        }
    }

    @PostMapping(value = "/")
    @Operation(summary = "Создание данных аудентификация пользователя")
    public ResponseEntity<String> create(@RequestBody
                                         @Parameter(description = "Данные для аудентификации")
                                         RequestAuthenticationDto requestAuthenticationDto) {
        authenticationService.create(requestAuthenticationDto);
        String userName = requestAuthenticationDto.getUserName();
        RequestRightDto requestRightDto = new RequestRightDto();
        requestRightDto.setRight(Right.user);
        requestRightDto.setUserName(userName);
        rightService.create(requestRightDto);
        accountService.create(userName);
        return ResponseEntity.ok(authenticationService.getJWTToken(requestAuthenticationDto.getUserName()));
    }


    @DeleteMapping(value = "/{userName}")
    @Operation(summary = "Удаление данных авторизации по userName")
    public ResponseEntity<Void> delete(@PathVariable
                                       @Parameter(description = "Имя пользователя")
                                       String userName) {
        authenticationService.delete(userName);
        return ResponseEntity.noContent().build();
    }
}

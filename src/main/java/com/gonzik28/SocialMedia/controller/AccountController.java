package com.gonzik28.SocialMedia.controller;

import com.gonzik28.SocialMedia.dto.RequestAccountDto;
import com.gonzik28.SocialMedia.dto.ResponseAccountDto;
import com.gonzik28.SocialMedia.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/account")
@Tag(name = "Аккаунт пользователя", description = "Создан для работы с аккаунтом и его функциями.")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(value = "/{userName}")
    @Operation(summary = "Получение аккаунта по id в данном случае userName уникален")
    public ResponseEntity<ResponseAccountDto> findByUserName(@PathVariable
                                                             @Parameter(description = "Имя пользователя")
                                                             String userName) {
        if (accountService.findByUserName(userName) != null) {
            ResponseAccountDto rightDto = accountService.findByUserName(userName);
            return ResponseEntity.ok(rightDto);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping(value = "/subscriber/{userName}")
    @Operation(summary = "Получение списка подписчиков по userName")
    public ResponseEntity<List<ResponseAccountDto>> getSubscriber(@PathVariable
                                                                  @Parameter(description = "Имя пользователя")
                                                                  String userName) {
        List<ResponseAccountDto> rightDto = accountService.getSubscribers(userName);
        return ResponseEntity.ok(rightDto);
    }

    @PostMapping(value = "/{userName}")
    @Operation(summary = "Создание аккаунта по userName с дефолтными значениями (аккаунт не заблокирован, " +
            "срок действия данных корректен)")
    public ResponseEntity<ResponseAccountDto> create(@PathVariable
                                                     @Parameter(description = "Имя пользователя")
                                                     String userName) {
        ResponseAccountDto rightDto = accountService.create(userName);
        return ResponseEntity.ok(rightDto);
    }

    @PostMapping(value = "/")
    @Operation(summary = "Изменение дефолтных значений аккаунта")
    public ResponseEntity<ResponseAccountDto> update(@RequestBody
                                                     @Parameter(description = "Измененные данные аккаунта")
                                                     RequestAccountDto requestAccountDto) {
        ResponseAccountDto rightDto = accountService.update(requestAccountDto);
        return ResponseEntity.ok(rightDto);
    }

    @DeleteMapping(value = "/{userName}")
    @Operation(summary = "Удаление аккаунта по userName")
    public ResponseEntity<Void> delete(@PathVariable
                                       @Parameter(description = "Имя пользователя")
                                       String userName) {
        accountService.delete(userName);
        return ResponseEntity.noContent().build();
    }
}

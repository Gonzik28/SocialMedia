package com.gonzik28.SocialMedia.controller;

import com.gonzik28.SocialMedia.config.Right;
import com.gonzik28.SocialMedia.dto.RequestRightDto;
import com.gonzik28.SocialMedia.dto.ResponseRightDto;
import com.gonzik28.SocialMedia.service.RightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/right")
@Tag(name = "Права доступа пользователя", description = "Создан для работы с правами доступа.")
public class RightController {
    private final RightService rightService;

    public RightController(RightService rightService) {
        this.rightService = rightService;
    }

    @PostMapping(value = "/add/{userName}/{right}")
    @Operation(summary = "Добавление прав доступа с указанием прав доступа конкретного пользователя")
    public ResponseEntity<ResponseRightDto> addRight(@PathVariable
                                                     @Parameter(description = "Имя пользователя")
                                                     String userName,
                                                     @PathVariable
                                                     @Parameter(description = "Вид прав доступа (user/admin)")
                                                     Right right) {
        RequestRightDto requestRightDto = new RequestRightDto();
        requestRightDto.setRight(right);
        requestRightDto.setUserName(userName);
        ResponseRightDto rightDto = rightService.create(requestRightDto);
        return ResponseEntity.ok(rightDto);
    }

    @DeleteMapping(value = "remove/{userName}/{right}")
    @Operation(summary = "Удаление данных авторизации по userName с указанием прав доступа")
    public ResponseEntity<Void> removeRight(@PathVariable
                                            @Parameter(description = "Имя пользователя")
                                            String userName,
                                            @PathVariable
                                            @Parameter(description = "Вид прав доступа (user/admin)")
                                            Right right) {
        RequestRightDto requestRightDto = new RequestRightDto();
        requestRightDto.setRight(right);
        requestRightDto.setUserName(userName);
        rightService.delete(requestRightDto);
        return ResponseEntity.noContent().build();
    }
}

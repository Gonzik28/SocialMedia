package com.gonzik28.SocialMedia.controller;

import com.gonzik28.SocialMedia.dto.RequestFriendshipDto;
import com.gonzik28.SocialMedia.dto.ResponseFriendshipDto;
import com.gonzik28.SocialMedia.service.FriendshipService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/friend")
@Tag(name = "Подписчики и друзья пользователя", description = "Создан для работы со списками друзей и подписчиков.")
public class FriendshipController {

    private final FriendshipService friendshipService;

    public FriendshipController(FriendshipService friendshipService) {
        this.friendshipService = friendshipService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение данных о подписке по идентификатору")
    public ResponseEntity<ResponseFriendshipDto> getFriendshipById(@PathVariable
                                                                   @Parameter(description =
                                                                           "Идентификатор записи о подписке")
                                                                   Long id) {
        ResponseFriendshipDto friendshipDto = friendshipService.findById(id);
        return ResponseEntity.ok(friendshipDto);
    }

    @PostMapping("/create")
    @Operation(summary = "Создание связи отправитель - получатель")
    public ResponseEntity<ResponseFriendshipDto> createFriendship(@RequestBody
                                                                  @Parameter(description =
                                                                          "Данные для создания связи")
                                                                  RequestFriendshipDto requestFriendshipDto) {
        ResponseFriendshipDto friendshipDto = friendshipService.create(requestFriendshipDto);
        return ResponseEntity.ok(friendshipDto);
    }

    @PostMapping("/{senderUserName}/{receiverUserName}/{friend}")
    @Operation(summary = "Подтверждение или отказ от дружбы")
    public ResponseEntity<ResponseFriendshipDto> addFriend(@PathVariable
                                                           @Parameter(description =
                                                                   "Имя пользователя, которому предложили дружбу")
                                                           String senderUserName,
                                                           @PathVariable
                                                           @Parameter(description =
                                                                   "Имя пользователя, который предложил дружить")
                                                           String receiverUserName,
                                                           @PathVariable
                                                           @Parameter(description =
                                                                   "Ответ на дружбу согласие (true)/ отказ (false)")
                                                           boolean friend) {
        ResponseFriendshipDto friendshipDto = friendshipService.addFriend(senderUserName, receiverUserName, friend);
        return ResponseEntity.ok(friendshipDto);
    }

    @DeleteMapping("/deleteFriend/{senderUserName}&{receiverUserName}")
    @Operation(summary = "Отказ от дружбы и подписки")
    public ResponseEntity<Void> deleteFriend(@Parameter(description = "Имя пользователя, который решил отписаться")
                                             String senderUserName,
                                             @PathVariable
                                             @Parameter(description = "Имя пользователя, от которого отписывается")
                                             String receiverUserName) {
        friendshipService.deleteFriend(senderUserName, receiverUserName);
        return ResponseEntity.noContent().build();
    }


    @PostMapping("/update")
    @Operation(summary = "Изменение связи отправитель - получатель")
    public ResponseEntity<ResponseFriendshipDto> updateFriendship(@RequestBody
                                                                  @Parameter(description =
                                                                          "Данные для изменения связи")
                                                                  RequestFriendshipDto requestFriendshipDto) {
        ResponseFriendshipDto friendshipDto = friendshipService.update(requestFriendshipDto);
        return ResponseEntity.ok(friendshipDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление данных о подписке по идентификатору")
    public ResponseEntity<Void> deleteFriendship(@PathVariable
                                                 @Parameter(description = "Идентификатор записи о подписке")
                                                 Long id) {
        friendshipService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

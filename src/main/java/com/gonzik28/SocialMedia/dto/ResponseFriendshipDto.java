package com.gonzik28.SocialMedia.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Данные о связи (подписчик/друг)")
public class ResponseFriendshipDto {
    @Schema(description = "Идентификатор записи о связи")
    private Long id;
    @Schema(description = "Пользователь, чьи публикации получены")
    private ResponseAccountDto sender;
    @Schema(description = "Пользователь, который получает публикации")
    private ResponseAccountDto receiver;
    @Schema(description = "Критерий дружбы (друзья - true, подписчик - false)")
    private boolean friend;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ResponseAccountDto getSender() {
        return sender;
    }

    public void setSender(ResponseAccountDto sender) {
        this.sender = sender;
    }

    public ResponseAccountDto getReceiver() {
        return receiver;
    }

    public void setReceiver(ResponseAccountDto receiver) {
        this.receiver = receiver;
    }

    public boolean isFriend() {
        return friend;
    }

    public void setFriend(boolean friend) {
        this.friend = friend;
    }
}

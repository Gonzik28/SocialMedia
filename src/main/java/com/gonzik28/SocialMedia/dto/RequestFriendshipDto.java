package com.gonzik28.SocialMedia.dto;

import io.swagger.v3.oas.annotations.media.Schema;
@Schema(description = "Запрашиваемые данные для создания связи (подписчик/друг)")
public class RequestFriendshipDto {
    @Schema(description = "Идентификатор записи о связи")
    private Long id;
    @Schema(description = "Имя пользователя, чьи публикации будут получены")
    private String senderUserName;
    @Schema(description = "Имя пользователя, который будет получать публикации")
    private String receiverUserName;
    @Schema(description = "Критерий дружбы (друзья - true, подписчик - false)")
    private boolean friend;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSenderUserName() {
        return senderUserName;
    }

    public void setSenderUserName(String senderUserName) {
        this.senderUserName = senderUserName;
    }

    public String getReceiverUserName() {
        return receiverUserName;
    }

    public void setReceiverUserName(String receiverUserName) {
        this.receiverUserName = receiverUserName;
    }


    public boolean isFriend() {
        return friend;
    }

    public void setFriend(boolean friend) {
        this.friend = friend;
    }


}

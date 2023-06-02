package com.gonzik28.SocialMedia.dto;

import com.gonzik28.SocialMedia.config.Right;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Запрашиваемые данные о правах доступа пользователя")
public class RequestRightDto {
    @Schema(description = "Имя пользователя")
    private String userName;
    @Schema(description = "Право доступа")
    private Right right;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Right getRight() {
        return right;
    }

    public void setRight(Right right) {
        this.right = right;
    }
}

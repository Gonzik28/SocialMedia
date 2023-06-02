package com.gonzik28.SocialMedia.dto;

import io.swagger.v3.oas.annotations.media.Schema;
@Schema(description = "Данные об аудентификации")
public class ResponseAuthenticationDto {
    @Schema(description = "Имя пользователя")
    private String userName;
    @Schema(description = "Email")
    private String email;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

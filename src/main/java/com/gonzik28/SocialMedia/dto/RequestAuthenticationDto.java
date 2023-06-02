package com.gonzik28.SocialMedia.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Запрашиваемые данные для аудентификации")
public class RequestAuthenticationDto {
    @Schema(description = "Имя пользователя")
    private String userName;
    @Schema(description = "Email")
    private String email;
    @Schema(description = "Пароль")
    private String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

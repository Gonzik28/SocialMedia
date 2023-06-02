package com.gonzik28.SocialMedia.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Запрашиваемые параметры аккаунта")
public class RequestAccountDto {
    @Schema(description = "Имя пользователя")
    private String userName;
    @Schema(description = "Корректные данные")
    private boolean enabled;
    @Schema(description = "Аккаунт просрочен")
    private boolean expiredAccount;
    @Schema(description = "Срок действия данных истек")
    private boolean expiredCredentials;
    @Schema(description = "Аккаунт заблокирован")
    private boolean lockedAccount;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isExpiredAccount() {
        return expiredAccount;
    }

    public void setExpiredAccount(boolean expiredAccount) {
        this.expiredAccount = expiredAccount;
    }

    public boolean isExpiredCredentials() {
        return expiredCredentials;
    }

    public void setExpiredCredentials(boolean expiredCredentials) {
        this.expiredCredentials = expiredCredentials;
    }

    public boolean isLockedAccount() {
        return lockedAccount;
    }

    public void setLockedAccount(boolean lockedAccount) {
        this.lockedAccount = lockedAccount;
    }

}

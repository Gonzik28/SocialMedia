package com.gonzik28.SocialMedia.dto;

import com.gonzik28.SocialMedia.config.Right;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;
@Schema(description = "Данные аккаунта")
public class ResponseAccountDto {
    @Schema(description = "Имя пользователя")
    private String userName;
    @Schema(description = "Права доступа пользователя")
    private List<Right> rights;
    @Schema(description = "Корректные данные")
    private boolean enabled;
    @Schema(description = "Аккаунт просрочен")
    private boolean expiredAccount;
    @Schema(description = "Срок действия данных истек")
    private boolean expiredCredentials;
    @Schema(description = "Аккаунт заблокирован")
    private boolean lockedAccount;
    @Schema(description = "Публикации пользователя")
    private List<ResponsePostDto> posts;
    @Schema(description = "Лента активности пользователя")
    private List<ResponsePostDto> activityFeed;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Right> getRights() {
        return rights;
    }

    public void setRights(List<Right> rights) {
        this.rights = rights;
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

    public List<ResponsePostDto> getPosts() {
        return posts;
    }

    public void setPosts(List<ResponsePostDto> posts) {
        this.posts = posts;
    }

    public List<ResponsePostDto> getActivityFeed() {
        return activityFeed;
    }

    public void setActivityFeed(List<ResponsePostDto> activityFeed) {
        this.activityFeed = activityFeed;
    }
}

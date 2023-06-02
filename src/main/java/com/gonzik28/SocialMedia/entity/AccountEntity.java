package com.gonzik28.SocialMedia.entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = AccountEntity.TABLE_NAME)
public class AccountEntity {
    public static final String TABLE_NAME = "account";
    @Id
    private String userName;
    private boolean enabled;
    private boolean expiredAccount;
    private boolean expiredCredentials;
    private boolean lockedAccount;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_name")
    private List<RightEntity> rights;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<PostEntity> posts;

    @ManyToMany
    @JoinTable(
            name = "friend_request",
            joinColumns = @JoinColumn(name = "sender_user_name"),
            inverseJoinColumns = @JoinColumn(name = "receiver_user_name")
    )
    private List<AccountEntity> friends;

    @ManyToMany(mappedBy = "friends")
    private List<AccountEntity> subscribers;


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

    public List<RightEntity> getRights() {
        return rights;
    }

    public List<PostEntity> getPosts() {
        return posts;
    }

    public List<AccountEntity> getSubscribers() {
        return subscribers;
    }

}
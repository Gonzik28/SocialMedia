package com.gonzik28.SocialMedia.entity;

import javax.persistence.*;

@Entity
@Table(name = FriendshipRequestEntity.TABLE_NAME)
public class FriendshipRequestEntity {
    public static final String TABLE_NAME = "friend_request";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "sender_user_name")
    private AccountEntity sender;
    @ManyToOne
    @JoinColumn(name = "receiver_user_name")
    private AccountEntity receiver;
    private boolean friend;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountEntity getSender() {
        return sender;
    }

    public void setSender(AccountEntity sender) {
        this.sender = sender;
    }

    public AccountEntity getReceiver() {
        return receiver;
    }

    public void setReceiver(AccountEntity receiver) {
        this.receiver = receiver;
    }

    public boolean isFriend() {
        return friend;
    }

    public void setFriend(boolean friend) {
        this.friend = friend;
    }

}

package com.gonzik28.SocialMedia.entity;

import com.gonzik28.SocialMedia.config.Right;

import javax.persistence.*;

@Entity
@Table(name = RightEntity.TABLE_NAME)
@IdClass(RightsId.class)
public class RightEntity {
    public static final String TABLE_NAME = "user_rights";
    @Id
    @Column(name = "user_name")
    private String userName;
    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "rights")
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

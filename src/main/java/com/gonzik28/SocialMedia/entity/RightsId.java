package com.gonzik28.SocialMedia.entity;

import com.gonzik28.SocialMedia.config.Right;

import java.io.Serializable;
import java.util.Objects;

public class RightsId implements Serializable {
    private String userName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RightsId)) {
            return false;
        }
        RightsId rightsId = (RightsId) o;
        return Objects.equals(getUserName(), rightsId.getUserName()) && getRight() == rightsId.getRight();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserName(), getRight());
    }
}

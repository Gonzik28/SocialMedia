package com.gonzik28.SocialMedia.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = PostEntity.TABLE_NAME)
public class PostEntity {
    public static final String TABLE_NAME = "posts";
    @Id
    private String id;
    private String header;
    private String post;
    private String postcard;
    @ManyToOne
    @JoinColumn(name = "user_name")
    private AccountEntity account;
    private LocalDateTime createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getPostcard() {
        return postcard;
    }

    public void setPostcard(String postcard) {
        this.postcard = postcard;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    @PrePersist
    protected void onCreate() {
        this.createDate = LocalDateTime.now();
    }

}

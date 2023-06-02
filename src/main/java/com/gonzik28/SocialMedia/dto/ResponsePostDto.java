package com.gonzik28.SocialMedia.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Данные о публикации")
public class ResponsePostDto {
    @Schema(description = "Заголовок публикации")
    private String header;
    @Schema(description = "Текст публикации")
    private String post;
    @Schema(description = "Ссылка (на сервере) на картинку в публикации")
    private String postcard;

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
}

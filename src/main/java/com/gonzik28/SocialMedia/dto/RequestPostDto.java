package com.gonzik28.SocialMedia.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.multipart.MultipartFile;
@Schema(description = "Запрашиваемые данные для создания публикации")
public class RequestPostDto {
    @Schema(description = "Идентификатор публикации")
    private String id;
    @Schema(description = "Заголовок публикации")
    private String header;
    @Schema(description = "Текст публикации")
    private String post;
    @Schema(description = "Ссылка (на сервере) на картинку в публикации")
    private String postcard;

    @Schema(description = "Автор публикации")
    private String userName;
    @Schema(description = "Картинка в публикации")
    private MultipartFile postcardImage;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public MultipartFile getPostcardImage() {
        return postcardImage;
    }

    public void setPostcardImage(MultipartFile postcardImage) {
        this.postcardImage = postcardImage;
    }

}

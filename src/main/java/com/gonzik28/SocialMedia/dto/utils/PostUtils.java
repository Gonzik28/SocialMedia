package com.gonzik28.SocialMedia.dto.utils;

import com.gonzik28.SocialMedia.dto.RequestPostDto;
import com.gonzik28.SocialMedia.dto.ResponsePostDto;
import com.gonzik28.SocialMedia.entity.AccountEntity;
import com.gonzik28.SocialMedia.entity.PostEntity;

import java.util.List;
import java.util.stream.Collectors;

public class PostUtils {

    public static ResponsePostDto postEntityToResponse(PostEntity postEntity) {
        ResponsePostDto responsePostDto = new ResponsePostDto();
        responsePostDto.setPost(postEntity.getPost());
        responsePostDto.setHeader(postEntity.getHeader());
        responsePostDto.setPostcard(postEntity.getPostcard());
        return responsePostDto;
    }

    public static List<ResponsePostDto> postEntityToResponses(List<PostEntity> postEntities) {
        return postEntities.stream().map(postEntity -> PostUtils.postEntityToResponse(postEntity))
                .collect(Collectors.toList());
    }


    public static PostEntity authenticationRequestToEntity(RequestPostDto requestPostDto, AccountEntity account) {
        PostEntity postEntity = new PostEntity();
        postEntity.setId(requestPostDto.getId());
        postEntity.setAccount(account);
        postEntity.setHeader(requestPostDto.getHeader());
        postEntity.setPost(requestPostDto.getPost());
        postEntity.setPostcard(requestPostDto.getPostcard());
        return postEntity;
    }
}

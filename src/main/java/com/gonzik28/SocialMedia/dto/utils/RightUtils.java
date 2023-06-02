package com.gonzik28.SocialMedia.dto.utils;

import com.gonzik28.SocialMedia.dto.RequestRightDto;
import com.gonzik28.SocialMedia.dto.ResponseRightDto;
import com.gonzik28.SocialMedia.entity.RightEntity;


public class RightUtils {
    public static ResponseRightDto rightDtoEntityToResponse(RightEntity rightEntity) {
        ResponseRightDto responseRightDto = new ResponseRightDto();
        responseRightDto.setRight(rightEntity.getRight());
        responseRightDto.setUserName(rightEntity.getUserName());
        return responseRightDto;
    }

    public static RightEntity rightRequestToEntity(RequestRightDto requestRightDto) {
        RightEntity rightEntity = new RightEntity();
        rightEntity.setRight(requestRightDto.getRight());
        rightEntity.setUserName(requestRightDto.getUserName());
        return rightEntity;
    }
}

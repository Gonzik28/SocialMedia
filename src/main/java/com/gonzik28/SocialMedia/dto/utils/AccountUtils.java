package com.gonzik28.SocialMedia.dto.utils;

import com.gonzik28.SocialMedia.config.Right;
import com.gonzik28.SocialMedia.dto.ResponseAccountDto;
import com.gonzik28.SocialMedia.dto.ResponsePostDto;
import com.gonzik28.SocialMedia.entity.AccountEntity;
import com.gonzik28.SocialMedia.entity.PostEntity;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AccountUtils {
    public static ResponseAccountDto accountDtoEntityToResponse(AccountEntity accountEntity) {
        ResponseAccountDto responseAccountDto = new ResponseAccountDto();
        responseAccountDto.setUserName(accountEntity.getUserName());
        responseAccountDto.setEnabled(accountEntity.isEnabled());
        responseAccountDto.setExpiredAccount(accountEntity.isExpiredAccount());
        responseAccountDto.setExpiredCredentials(accountEntity.isExpiredCredentials());
        responseAccountDto.setLockedAccount(accountEntity.isLockedAccount());
        if (accountEntity.getRights() != null) {
            List<Right> rights = accountEntity.getRights().stream().map(x -> x.getRight()).collect(Collectors.toList());
            responseAccountDto.setRights(rights);
        }
        if (accountEntity.getPosts() != null) {
            responseAccountDto.setPosts(PostUtils.postEntityToResponses(accountEntity.getPosts()));
        }
        if (accountEntity.getSubscribers() != null) {
            List<ResponsePostDto> activityFeed = accountEntity.getSubscribers().stream()
                    .flatMap(subscriber -> subscriber.getPosts().stream())
                    .sorted(Comparator.comparing(PostEntity::getCreateDate).reversed())
                    .map(PostUtils::postEntityToResponse)
                    .collect(Collectors.toList());
            responseAccountDto.setActivityFeed(activityFeed);
        }
        return responseAccountDto;
    }
}

package com.gonzik28.SocialMedia.dto.utils;

import com.gonzik28.SocialMedia.dto.RequestFriendshipDto;
import com.gonzik28.SocialMedia.dto.ResponseAccountDto;
import com.gonzik28.SocialMedia.dto.ResponseFriendshipDto;
import com.gonzik28.SocialMedia.entity.AccountEntity;
import com.gonzik28.SocialMedia.entity.FriendshipRequestEntity;


public class FriendshipUtils {

    public static ResponseFriendshipDto friendshipEntityToResponse(FriendshipRequestEntity friendshipEntity) {
        ResponseFriendshipDto responseFriendshipDto = new ResponseFriendshipDto();
        responseFriendshipDto.setId(friendshipEntity.getId());
        ResponseAccountDto receiver = AccountUtils.accountDtoEntityToResponse(friendshipEntity.getReceiver());
        ResponseAccountDto sender = AccountUtils.accountDtoEntityToResponse(friendshipEntity.getSender());
        responseFriendshipDto.setReceiver(receiver);
        responseFriendshipDto.setSender(sender);
        responseFriendshipDto.setFriend(friendshipEntity.isFriend());
        return responseFriendshipDto;
    }


    public static FriendshipRequestEntity friendshipRequestToEntity(RequestFriendshipDto friendshipDto,
                                                                    AccountEntity receiver,
                                                                    AccountEntity sender) {
        FriendshipRequestEntity friendshipEntity = new FriendshipRequestEntity();
        friendshipEntity.setId(friendshipDto.getId());
        friendshipEntity.setReceiver(receiver);
        friendshipEntity.setSender(sender);
        friendshipEntity.setFriend(friendshipDto.isFriend());
        return friendshipEntity;
    }
}

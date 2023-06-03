package com.gonzik28.SocialMedia.service;

import com.gonzik28.SocialMedia.dto.*;
import com.gonzik28.SocialMedia.dto.utils.FriendshipUtils;
import com.gonzik28.SocialMedia.entity.AccountEntity;
import com.gonzik28.SocialMedia.entity.FriendshipRequestEntity;
import com.gonzik28.SocialMedia.repository.AccountRepository;
import com.gonzik28.SocialMedia.repository.FriendshipRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class FriendshipService {
    private final FriendshipRepository friendshipRepository;
    private final AccountRepository accountRepository;

    public FriendshipService(FriendshipRepository friendshipRepository, AccountRepository accountRepository) {
        this.friendshipRepository = friendshipRepository;
        this.accountRepository = accountRepository;
    }

    public ResponseFriendshipDto findById(Long id) {
        if (friendshipRepository.findById(id).isPresent()) {
            FriendshipRequestEntity friendshipRequestEntity = friendshipRepository.findById(id).get();
            return FriendshipUtils.friendshipEntityToResponse(friendshipRequestEntity);
        } else {
            return null;
        }
    }

    public ResponseFriendshipDto create(RequestFriendshipDto requestFriendshipDto) {
        if (friendshipRepository.findById(requestFriendshipDto.getId()).isPresent()) {
            return update(requestFriendshipDto);
        }
        if (accountRepository.findByUserName(requestFriendshipDto.getReceiverUserName()).isPresent()
                && accountRepository.findByUserName(requestFriendshipDto.getSenderUserName()).isPresent()) {
            AccountEntity receiver = accountRepository
                    .findByUserName(requestFriendshipDto.getReceiverUserName()).get();
            AccountEntity sender = accountRepository
                    .findByUserName(requestFriendshipDto.getSenderUserName()).get();
            FriendshipRequestEntity friendshipRequestEntity = FriendshipUtils
                    .friendshipRequestToEntity(requestFriendshipDto, receiver, sender);
            friendshipRequestEntity = friendshipRepository.save(friendshipRequestEntity);
            return FriendshipUtils.friendshipEntityToResponse(friendshipRequestEntity);
        } else {
            throw new RuntimeException("Not find receiver or sender");
        }
    }


    public ResponseFriendshipDto addFriend(String senderUserName, String receiverUserName, boolean friend) {
        Optional<AccountEntity> sender = accountRepository.findByUserName(senderUserName);
        Optional<AccountEntity> receiver = accountRepository.findByUserName(receiverUserName);
        if (sender.isPresent() && receiver.isPresent()) {
            Optional<FriendshipRequestEntity> friendshipRequest =
                    friendshipRepository.findBySenderAndReceiver(sender.get(), receiver.get());
            if (friendshipRequest.isPresent()) {
                FriendshipRequestEntity friendshipRequestEntity = friendshipRequest.get();
                friendshipRequestEntity.setFriend(friend);
                friendshipRepository.save(friendshipRequestEntity);
                if (friend) {
                    FriendshipRequestEntity friendship = new FriendshipRequestEntity();
                    friendship.setFriend(true);
                    friendship.setReceiver(friendshipRequestEntity.getSender());
                    friendship.setSender(friendshipRequestEntity.getReceiver());
                    friendship = friendshipRepository.save(friendship);
                    return FriendshipUtils.friendshipEntityToResponse(friendship);
                } else {
                    return FriendshipUtils.friendshipEntityToResponse(friendshipRequestEntity);
                }
            } else {
                throw new RuntimeException("Not find friendship where" +
                        " sender " + senderUserName + " receiver " + receiverUserName);
            }
        } else {
            throw new RuntimeException("Not find sender " + senderUserName + " receiver " + receiverUserName);
        }
    }

    public void deleteFriend(String senderUserName, String receiverUserName) {
        Optional<AccountEntity> sender = accountRepository.findByUserName(senderUserName);
        Optional<AccountEntity> receiver = accountRepository.findByUserName(receiverUserName);
        if (sender.isPresent() && receiver.isPresent()) {
            Optional<FriendshipRequestEntity> friendshipRequest =
                    friendshipRepository.findBySenderAndReceiver(sender.get(), receiver.get());
            if (friendshipRequest.isPresent()) {
                FriendshipRequestEntity friendshipRequestEntity = friendshipRequest.get();
                if (friendshipRequestEntity.isFriend()) {
                    Optional<FriendshipRequestEntity> friendshipRequestTwo =
                            friendshipRepository.findBySenderAndReceiver(receiver.get(), sender.get());
                    if (friendshipRequestTwo.isPresent()) {
                        FriendshipRequestEntity friendshipRequestEntityTwo = friendshipRequestTwo.get();
                        friendshipRequestEntityTwo.setFriend(false);
                        friendshipRepository.save(friendshipRequestEntityTwo);
                    } else {
                        throw new RuntimeException("Not found friendship");
                    }
                }
                friendshipRepository.deleteById(friendshipRequestEntity.getId());
            } else {
                throw new RuntimeException("Not find friendship where" +
                        " sender " + senderUserName + " receiver " + receiverUserName);
            }
        } else {
            throw new RuntimeException("Not find sender " + senderUserName + " receiver " + receiverUserName);
        }
    }

    public ResponseFriendshipDto update(RequestFriendshipDto requestFriendshipDto) {
        Long id = requestFriendshipDto.getId();
        if (friendshipRepository.findById(id).isPresent()) {
            FriendshipRequestEntity friendshipRequestEntity = friendshipRepository.findById(id).get();
            if (accountRepository.findByUserName(requestFriendshipDto.getReceiverUserName()).isPresent()
                    && accountRepository.findByUserName(requestFriendshipDto.getSenderUserName()).isPresent()) {
                AccountEntity receiver = accountRepository
                        .findByUserName(requestFriendshipDto.getReceiverUserName()).get();
                AccountEntity sender = accountRepository
                        .findByUserName(requestFriendshipDto.getSenderUserName()).get();
                friendshipRequestEntity.setSender(sender);
                friendshipRequestEntity.setReceiver(receiver);
                friendshipRequestEntity.setFriend(requestFriendshipDto.isFriend());
                friendshipRequestEntity = friendshipRepository.save(friendshipRequestEntity);
                return FriendshipUtils.friendshipEntityToResponse(friendshipRequestEntity);
            } else {
                throw new RuntimeException("Not find receiver or sender");
            }
        } else {
            return create(requestFriendshipDto);
        }
    }

    public void delete(Long id) {
        friendshipRepository.deleteById(id);
    }
}

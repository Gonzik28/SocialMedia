package com.gonzik28.SocialMedia.repository;

import com.gonzik28.SocialMedia.entity.AccountEntity;
import com.gonzik28.SocialMedia.entity.FriendshipRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FriendshipRepository extends JpaRepository<FriendshipRequestEntity, Long> {
    Optional<FriendshipRequestEntity> findBySenderAndReceiver(AccountEntity senderUserName,
                                                              AccountEntity receiverUserName);
}

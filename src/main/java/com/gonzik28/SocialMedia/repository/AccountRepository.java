package com.gonzik28.SocialMedia.repository;

import com.gonzik28.SocialMedia.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountEntity, String> {
    Optional<AccountEntity> findByUserName(String userName);
}

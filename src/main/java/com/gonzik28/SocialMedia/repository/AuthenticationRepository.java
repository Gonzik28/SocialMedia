package com.gonzik28.SocialMedia.repository;

import com.gonzik28.SocialMedia.entity.AuthenticationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthenticationRepository extends JpaRepository<AuthenticationEntity, String> {
     Optional<AuthenticationEntity> findByUserName(String userName);
    Optional<AuthenticationEntity> findByEmail(String email);
}

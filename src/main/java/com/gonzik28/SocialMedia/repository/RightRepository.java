package com.gonzik28.SocialMedia.repository;

import com.gonzik28.SocialMedia.entity.RightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RightRepository extends JpaRepository<RightEntity, String> {
    List<RightEntity> findAllByUserName(String userName);
}

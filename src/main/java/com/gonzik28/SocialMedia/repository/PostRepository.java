package com.gonzik28.SocialMedia.repository;

import com.gonzik28.SocialMedia.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PostRepository extends JpaRepository<PostEntity, String> {
}

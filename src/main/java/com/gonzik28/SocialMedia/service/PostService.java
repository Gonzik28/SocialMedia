package com.gonzik28.SocialMedia.service;

import com.gonzik28.SocialMedia.dto.RequestPostDto;
import com.gonzik28.SocialMedia.dto.ResponsePostDto;
import com.gonzik28.SocialMedia.dto.utils.FileUtil;
import com.gonzik28.SocialMedia.dto.utils.PostUtils;
import com.gonzik28.SocialMedia.entity.AccountEntity;
import com.gonzik28.SocialMedia.entity.PostEntity;
import com.gonzik28.SocialMedia.repository.AccountRepository;
import com.gonzik28.SocialMedia.repository.PostRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.NoSuchElementException;


@Service
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final AccountRepository accountRepository;

    @Value("${upload-dir}")
    private String uploadDir;

    public PostService(PostRepository postRepository, AccountRepository accountRepository) {
        this.postRepository = postRepository;
        this.accountRepository = accountRepository;
    }

    public ResponsePostDto findById(String id) {
        if (postRepository.findById(id).isPresent()) {
            PostEntity postEntity = postRepository.findById(id).get();
            return PostUtils.postEntityToResponse(postEntity);
        } else {
            return null;
        }
    }

    public ResponsePostDto create(RequestPostDto requestPostDto) {
        if (!postRepository.findById(requestPostDto.getId()).isPresent()) {
            String userName = requestPostDto.getUserName();
            PostEntity postEntity;
            if (!accountRepository.findByUserName(userName).isPresent()) {
                throw new NoSuchElementException("Account not found with userName: " + userName);
            }
            postEntity = PostUtils.authenticationRequestToEntity(requestPostDto,
                    accountRepository.findByUserName(userName).get());
            if (requestPostDto.getPostcardImage() != null) {
                String fileName = StringUtils.cleanPath(requestPostDto.getPostcardImage().getOriginalFilename());
                String uploadDir = "C:/Users/Alena/Desktop/image";
                try {
                    FileUtil.saveFile(uploadDir, fileName, requestPostDto.getPostcardImage());
                    String postcardUrl = "/image/" + fileName;
                    postEntity.setPostcard(postcardUrl);
                } catch (IOException e) {
                    e.getStackTrace();
                }
            } else {
                postEntity.setPostcard(null);
            }
            postEntity.setId(requestPostDto.getId());
            postEntity = postRepository.save(postEntity);
            return PostUtils.postEntityToResponse(postEntity);
        } else {
            return update(requestPostDto);
        }
    }

    public ResponsePostDto update(RequestPostDto requestPostDto) {
        String id = requestPostDto.getId();
        if (postRepository.findById(id).isPresent()) {
            PostEntity postEntity = postRepository.findById(id).get();
            if (!accountRepository.findByUserName(requestPostDto.getUserName()).isPresent()) {
                throw new NoSuchElementException("Account not found with userName: " + requestPostDto.getUserName());
            }
            AccountEntity account = accountRepository.findByUserName(requestPostDto.getUserName()).get();
            postEntity.setAccount(account);
            postEntity.setHeader(requestPostDto.getHeader());
            postEntity.setPost(requestPostDto.getPost());

            MultipartFile postcardImage = requestPostDto.getPostcardImage();
            if (postcardImage != null) {
                String fileName = StringUtils.cleanPath(postcardImage.getOriginalFilename());
                try {
                    FileUtil.saveFile(uploadDir, fileName, postcardImage);
                    String postcardUrl = "/image/" + fileName;
                    postEntity.setPostcard(postcardUrl);
                } catch (IOException e) {
                    e.getStackTrace();
                }
            } else {
                postEntity.setPostcard(requestPostDto.getPostcard());
            }

            postEntity = postRepository.save(postEntity);
            return PostUtils.postEntityToResponse(postEntity);
        } else {
            return create(requestPostDto);
        }
    }

    public void delete(String id) {
        postRepository.deleteById(id);
    }
}

package com.gonzik28.SocialMedia.service;

import com.gonzik28.SocialMedia.dto.RequestRightDto;
import com.gonzik28.SocialMedia.dto.ResponseRightDto;
import com.gonzik28.SocialMedia.dto.utils.RightUtils;
import com.gonzik28.SocialMedia.entity.RightEntity;
import com.gonzik28.SocialMedia.entity.RightsId;
import com.gonzik28.SocialMedia.repository.RightRepository;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RightService {
    private final RightRepository rightRepository;

    public RightService(RightRepository rightRepository) {
        this.rightRepository = rightRepository;
    }

    public ResponseRightDto create(RequestRightDto requestRightDto) {
        if (rightRepository.findAllByUserName(requestRightDto.getUserName()).size() != 0) {
            List<RightEntity> rightEntities = rightRepository.findAllByUserName(requestRightDto.getUserName());
            if (!rightEntities.stream().anyMatch(entity -> entity.getRight().equals(requestRightDto.getRight()))) {
                RightEntity rightEntity = RightUtils.rightRequestToEntity(requestRightDto);
                rightEntity = rightRepository.save(rightEntity);
                return RightUtils.rightDtoEntityToResponse(rightEntity);
            } else {
                throw new DuplicateKeyException("right for userName already exists");
            }
        } else {
            RightEntity rightEntity = RightUtils.rightRequestToEntity(requestRightDto);
            rightEntity = rightRepository.save(rightEntity);
            return RightUtils.rightDtoEntityToResponse(rightEntity);
        }
    }

    public void delete(RequestRightDto requestRightDto) {
        RightsId rightsId = new RightsId();
        rightsId.setRight(requestRightDto.getRight());
        rightsId.setUserName(requestRightDto.getUserName());
        rightRepository.deleteById(String.valueOf(rightsId));
    }
}

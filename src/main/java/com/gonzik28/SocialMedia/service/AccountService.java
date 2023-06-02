package com.gonzik28.SocialMedia.service;

import com.gonzik28.SocialMedia.dto.RequestAccountDto;
import com.gonzik28.SocialMedia.dto.ResponseAccountDto;
import com.gonzik28.SocialMedia.dto.utils.AccountUtils;
import com.gonzik28.SocialMedia.entity.AccountEntity;
import com.gonzik28.SocialMedia.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public ResponseAccountDto findByUserName(String userName) {
        AccountEntity rightEntity = accountRepository.findByUserName(userName).get();
        return AccountUtils.accountDtoEntityToResponse(rightEntity);
    }

    public List<ResponseAccountDto> getSubscribers(String userName) {
        if (accountRepository.findByUserName(userName).isPresent()) {
            AccountEntity accountEntity = accountRepository.findByUserName(userName).get();
            List<AccountEntity> subscribers = accountEntity.getSubscribers();
            List<ResponseAccountDto> subscriberDtos = new ArrayList<>();
            for (AccountEntity subscriber : subscribers) {
                subscriberDtos.add(AccountUtils.accountDtoEntityToResponse(subscriber));
            }
            return subscriberDtos;
        } else {
            throw new RuntimeException("Пользователь не найден");
        }
    }

    public ResponseAccountDto create(String userName) {
        if (!accountRepository.findByUserName(userName).isPresent()) {
            AccountEntity rightEntity = new AccountEntity();
            rightEntity.setUserName(userName);
            rightEntity.setExpiredAccount(false);
            rightEntity.setExpiredCredentials(false);
            rightEntity.setEnabled(true);
            rightEntity.setLockedAccount(false);
            rightEntity = accountRepository.save(rightEntity);
            return AccountUtils.accountDtoEntityToResponse(rightEntity);
        } else {
            return null;
        }
    }

    public ResponseAccountDto update(RequestAccountDto requestAccountDto) {
        String userName = requestAccountDto.getUserName();
        if (accountRepository.findByUserName(userName).isPresent()) {
            AccountEntity rightEntity = accountRepository.findByUserName(userName).get();
            rightEntity.setEnabled(requestAccountDto.isEnabled());
            rightEntity.setExpiredAccount(requestAccountDto.isExpiredAccount());
            rightEntity.setExpiredCredentials(requestAccountDto.isExpiredCredentials());
            rightEntity.setLockedAccount(requestAccountDto.isLockedAccount());
            rightEntity = accountRepository.save(rightEntity);
            return AccountUtils.accountDtoEntityToResponse(rightEntity);
        } else {
            return create(requestAccountDto.getUserName());
        }
    }

    public void delete(String userName) {
        accountRepository.deleteById(userName);
    }

}

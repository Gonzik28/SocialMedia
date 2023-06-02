package com.gonzik28.SocialMedia.service;

import com.gonzik28.SocialMedia.config.EncoderConfig;
import com.gonzik28.SocialMedia.entity.AccountEntity;
import com.gonzik28.SocialMedia.entity.AuthenticationEntity;
import com.gonzik28.SocialMedia.entity.RightEntity;
import com.gonzik28.SocialMedia.repository.AccountRepository;
import com.gonzik28.SocialMedia.repository.AuthenticationRepository;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PostgresUserDetailsService implements UserDetailsService {

    private final AuthenticationRepository authenticationRepository;
    private final AccountRepository accountRepository;
    private final EncoderConfig encoderConfig;

    public PostgresUserDetailsService(AuthenticationRepository authenticationRepository, EncoderConfig encoderConfig,
                                      AccountRepository accountRepository) {
        this.authenticationRepository = authenticationRepository;
        this.accountRepository = accountRepository;
        this.encoderConfig = encoderConfig;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user;
        if (authenticationRepository.findByUserName(username).isPresent()) {
            AuthenticationEntity authorizationEntity = authenticationRepository.findByUserName(username).get();
            String passwordHash = authorizationEntity.getPassword();
            AccountEntity accountEntity = accountRepository.findByUserName(username).get();
            boolean enabled = accountEntity.isEnabled();
            boolean notExpiredAccount = !accountEntity.isExpiredAccount();//аккаунт не истек
            boolean notExpiredCredentials = !accountEntity.isExpiredCredentials();//учетные данные не просрочены
            boolean notLockedAccount = !accountEntity.isLockedAccount();//аккаунт не заблокирован
            List<RightEntity> rightEntities = accountEntity.getRights();
            String rights = rightEntities.stream()
                    .map(RightEntity::getRight)
                    .map(Enum::name)
                    .collect(Collectors.joining(","));
            user = new User(username, passwordHash, enabled, notExpiredAccount, notExpiredCredentials,
                    notLockedAccount, AuthorityUtils.commaSeparatedStringToAuthorityList(rights));
        } else {
            throw new UsernameNotFoundException("userName " + username + " Not found in the database");
        }

        return user;
    }
}

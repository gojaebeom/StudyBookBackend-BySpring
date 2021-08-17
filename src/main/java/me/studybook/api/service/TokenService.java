package me.studybook.api.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;

@Service
@Slf4j
public class TokenService {

    public static void isMatched(Long userId, Long tokenUserId) throws AuthenticationException {
        if(userId != tokenUserId){
            log.info("일치하지 않은 유저");
            throw new AuthenticationException("PERMISSION_NOT_DEFINE");
        }
    }
}

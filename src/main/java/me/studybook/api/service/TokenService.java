package me.studybook.api.service;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.studybook.api.config.ConfigProperties;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.util.Date;

@Slf4j
public class TokenService {


    static Date now = new Date();

    public static String createAct(Long userId, String profilePreview) {

        String accessToken = Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer("access")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + Duration.ofDays(1).toMillis()))
                .claim("id", userId)
                .claim("profile", profilePreview)
                .signWith(SignatureAlgorithm.HS256, "secret")
                .compact();

        return accessToken;
    }

    public static String createRft(Long userId) {
        String refreshToken = Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer("refresh")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + Duration.ofDays(365).toMillis()))
                .claim("id", userId)
                .signWith(SignatureAlgorithm.HS256, "secret")
                .compact();

        return refreshToken;
    }

    public static void isMatched(Long userId, Long tokenUserId) throws AuthenticationException {
        if(userId != tokenUserId){
            log.info("일치하지 않은 유저");
            throw new AuthenticationException("PERMISSION_NOT_DEFINE");
        }
    }
}

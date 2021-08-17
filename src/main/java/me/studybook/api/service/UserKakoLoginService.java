package me.studybook.api.service;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.studybook.api.domain.User;
import me.studybook.api.dto.res.ResUserLoginDto;
import me.studybook.api.repo.user.UserRepo;
import me.studybook.api.repo.user.mapper.UserMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.time.Duration;
import java.util.Date;
import java.util.Map;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class UserKakoLoginService {

    private UserRepo userRepo;

    public ResUserLoginDto kakaoLogin(String accessToken) throws Exception {
        /**
         *  카카오 accessToken을 이용해
         *  카카오에게 유저 정보를 요청,
         *  유저 정보중 고유 번호가 DB에 있는지 확인
         *  있다면 단순히 토큰 발급,
         *  없다면 유저 정보 중 유저 고유 번호를 [KAKAO.유저번호]
         *  로 만들고 책벌레[ 중복되지 않는 숫자 ]
         *  라는 닉네임을 발급하여 회원 등록 후 토큰 발급
         */
        String kakaoProviderId = requestKakaoUserInfo(accessToken);
        if(!isJoined(kakaoProviderId)){
            log.info("비회원, 회원가입 먼저 진행");
            joinUser(kakaoProviderId, getLastUserId());
        }
        log.info("로그인 진행");

        UserMapper user = userRepo.findUserByUsername(kakaoProviderId);
        String[] tokens = createJWTToken(user.getId());

        return ResUserLoginDto.builder()
                .accessToken(tokens[0])
                .userId(user.getId())
                .profile(user.getProfile())
                .refreshToken(tokens[1])
                .build();
    }

    private String[] createJWTToken(Long userId) {
        Date now = new Date();
        String accessToken = Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer("access")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + Duration.ofDays(1).toMillis()))
                .claim("id", userId)
                .signWith(SignatureAlgorithm.HS256, "secret")
                .compact();

        String refreshToken = Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer("refresh")
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + Duration.ofDays(365).toMillis()))
                .claim("id", userId)
                .signWith(SignatureAlgorithm.HS256, "secret")
                .compact();
        System.out.println(accessToken);

        return new String[]{accessToken, refreshToken};
    }

    private void joinUser(String username, Long lastUserId) throws Exception {
        final String DEFAULT_NICKNAME = "책벌레";
        System.out.println(lastUserId+1);
        User user = User.builder()
                .username(username)
                .nickname( DEFAULT_NICKNAME + (lastUserId+1) )
                .build();
        userRepo.save(user);
    }

    private Long getLastUserId() throws Exception {
        UserMapper user = userRepo.findFirstByOrderByIdDesc();
        System.out.println(user);
        return  user == null ? 0 : user.getId();
    }

    private boolean isJoined(String providerId) throws Exception {
        if(userRepo.countByUsername(providerId) <= 0){
            return false;
        }
        return true;
    }

    private String requestKakaoUserInfo(String accessToken) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization","Bearer "+accessToken);
        headers.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        HttpEntity entity = new HttpEntity(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> resultMap;
        try{
            resultMap = restTemplate.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.GET, entity, Map.class);
        }catch (HttpClientErrorException e){
            throw new Exception(e.getMessage());
        }
        String kakaoUserId = "KAKAO."+resultMap.getBody().get("id").toString();
        return kakaoUserId;
    }
}

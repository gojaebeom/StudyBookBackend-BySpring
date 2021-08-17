package me.studybook.api.interceptor;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.naming.AuthenticationException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.BindException;
import java.util.Enumeration;

@Slf4j
public class TokenValidationIC implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("토큰 유효성 검사 인터셉터");

        String tokenString = request.getHeader("Authorization");
        if(tokenString == null){
            throw new AuthenticationException("PERMISSION_NOT_DEFINE");
        }

        String token = tokenString.split("bearer ")[1];

        if(token == null){
            throw new AuthenticationException("PERMISSION_NOT_DEFINE");
        }

        try{
            Claims data = Jwts.parser()
                    .setSigningKey("secret")
                    .parseClaimsJws(token)
                    .getBody();
            log.info("id", data.get("id"));
            request.setAttribute("id", data.get("id"));
        }catch(ExpiredJwtException e){
            throw new BindException("EXPIRED_TOKEN");
        }catch(Exception e) {
            throw new BindException("PERMISSION_NOT_DEFINE");
        }

        return true;
    }
}

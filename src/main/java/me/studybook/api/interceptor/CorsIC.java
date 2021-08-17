package me.studybook.api.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class CorsIC implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("CORS SETTING");

        response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:3000"); // 허용 URL
        response.setHeader("Access-Control-Allow-Credentials", "true");   // 자격증명
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, DELETE, PUT"); // 허용 메소드
        response.setHeader("Access-Control-Max-Age", "3600");   // 브라우저 캐시 시간
        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With, content-type, enctype, Authorization");    // 허용 헤더 X-Requested-With, content-type, enctype, Authentication

        return true;
    }
}

package me.studybook.api.service;

import lombok.AllArgsConstructor;
import me.studybook.api.config.ConfigProperties;
import me.studybook.api.dto.res.ResNaverNewDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class NaverSearchService {

    private ConfigProperties configProperties;

    public List<Object> getApi() throws Exception{

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", configProperties.getNaverClientId());
        headers.set("X-Naver-Client-Secret", configProperties.getNaverClientSecret());
        HttpEntity entity = new HttpEntity(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> resultMap;
        try{
            resultMap = restTemplate.exchange("https://openapi.naver.com/v1/search/news.json?query=개발자", HttpMethod.GET, entity, Map.class);
        }catch (HttpClientErrorException e){
            throw new Exception(e.getMessage());
        }
        List<Object> results = (List<Object>) resultMap.getBody().get("items");
        Map<String, String> result = (Map<String, String>) results.get(0);
        System.out.println(result.get("title"));
        System.out.println(results.get(1));

        return results;
    }
}

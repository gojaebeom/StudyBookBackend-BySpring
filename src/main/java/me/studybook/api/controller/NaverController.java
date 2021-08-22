package me.studybook.api.controller;

import lombok.AllArgsConstructor;
import me.studybook.api.config.ConfigProperties;
import me.studybook.api.service.NaverSearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/api/naver")
public class NaverController {

    private NaverSearchService naverSearchService;

    @GetMapping("/news")
    public ResponseEntity index() throws Exception {

        List<Object> apis = naverSearchService.getApi();
        return ResponseEntity.ok().body(apis);
    }
}

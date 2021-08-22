package me.studybook.api.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.studybook.api.dto.req.ReqPostCreateDto;
import me.studybook.api.dto.req.ReqPostsDto;
import me.studybook.api.dto.res.ResPostDetailDto;
import me.studybook.api.dto.res.ResPostsDto;
import me.studybook.api.service.PostService;
import me.studybook.api.service.TokenService;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "https://studybook.me")
@RestController
@RequestMapping("/api/posts")
@Slf4j
@AllArgsConstructor
public class PostController {

    private PostService postService;


    @GetMapping("")
    public ResponseEntity index(ReqPostsDto postsDto) throws Exception{
        log.info("POST:INDEX");

        System.out.println(postsDto);

        List<ResPostsDto> posts =  postService.index(postsDto);
        Map<String, Object> responses = new HashMap<>();
        responses.put("message", "Get posts success");
        responses.put("posts", posts);
        responses.put("status", 200);
        return ResponseEntity.ok().body(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity show(@PathVariable Long id) throws Exception{
        log.info("POST:SHOW");
        ResPostDetailDto postDetail = postService.show(id);

        Map<String, Object> responses = new HashMap<>();
        responses.put("message", "Get posts success");
        responses.put("post", postDetail);
        responses.put("status", 200);
        return ResponseEntity.ok().body(responses);
    }

    @PostMapping("")
    public ResponseEntity create(ReqPostCreateDto postCreateDto, HttpServletRequest request) throws Exception {
//        log.info("POST:CREATE");
//        String _tokenId = request.getAttribute("id").toString();
//        Long tokenId = Long.parseLong(_tokenId);
//        TokenService.isMatched(postCreateDto.getUserId(), tokenId);

        postService.create(postCreateDto);

        Map<String, Object> responses = new HashMap<>();
        responses.put("message", "Post create success!");
        responses.put("status", 200);
        return ResponseEntity.ok().body(responses);
    }
}

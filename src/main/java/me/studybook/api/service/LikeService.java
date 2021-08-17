package me.studybook.api.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.studybook.api.domain.Post;
import me.studybook.api.domain.PostLike;
import me.studybook.api.domain.User;
import me.studybook.api.dto.req.ReqLikeDto;
import me.studybook.api.repo.post.PostLikeRepo;
import me.studybook.api.repo.post.PostRepo;
import me.studybook.api.repo.post.mapper.PostLikeMapper;
import me.studybook.api.repo.post.mapper.PostMapper;
import me.studybook.api.repo.user.UserRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import javax.transaction.Transactional;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class LikeService {

    private PostLikeRepo postLikeRepo;
    private UserRepo userRepo;
    private PostRepo postRepo;

    public String create(ReqLikeDto likeDto) throws Exception {
        // 조회
        PostLike postLike = getPostLike(likeDto.getUserId(), likeDto.getPostId());

        // 생성
        if(postLike == null){
            System.out.println("새로 생성");
            User user = userRepo.findUserById(likeDto.getUserId());
            Post post = postRepo.findPostById(likeDto.getPostId());
            postLike = likeDto.toPostLike(user, post);
            postLikeRepo.save(postLike);
            return likeDto.getUserId()+".User liked "+likeDto.getPostId()+".Post";
        }

        // 삭제
        destory(postLike);
        return likeDto.getUserId()+".User un-liked "+likeDto.getPostId()+".Post";
    }

    private PostLike getPostLike(Long userId, Long postId){
        return postLikeRepo.findPostLikeByUserIdAndPostId(userId, postId);
    }

    private void destory(PostLike postLike){
        postLikeRepo.delete(postLike);
    }
}

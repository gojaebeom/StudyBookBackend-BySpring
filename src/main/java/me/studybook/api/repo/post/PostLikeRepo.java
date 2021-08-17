package me.studybook.api.repo.post;

import me.studybook.api.domain.Post;
import me.studybook.api.domain.PostLike;
import me.studybook.api.domain.User;
import me.studybook.api.repo.post.mapper.PostLikeMapper;
import me.studybook.api.repo.post.mapper.PostMapper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeRepo extends JpaRepository<PostLike, Long> {
    Long countPostLikeByUserIdAndPostId(Long userId, Long postId) throws Exception;
    PostLike findPostLikeByUserIdAndPostId(Long userId, Long postId);
}

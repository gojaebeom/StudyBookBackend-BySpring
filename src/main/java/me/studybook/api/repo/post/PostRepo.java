package me.studybook.api.repo.post;

import me.studybook.api.domain.Post;
import me.studybook.api.repo.post.mapper.PostDetailMapper;
import me.studybook.api.repo.post.mapper.PostMapper;
import me.studybook.api.repo.post.mapper.PostsMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Long> {
    List<PostsMapper> findAllByOrderByIdDesc() throws Exception;
    List<PostsMapper> findAllByUserIdOrderByIdDesc(Long id) throws Exception;
    PostDetailMapper findOneById(Long id) throws Exception;
    List<PostsMapper> findAllByUserId(Long id) throws Exception;
    Post findPostById(Long postId) throws Exception;
}

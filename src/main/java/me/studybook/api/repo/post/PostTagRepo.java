package me.studybook.api.repo.post;

import me.studybook.api.domain.PostTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostTagRepo extends JpaRepository<PostTag, Long> {
    List<PostTag> findByPostId(Long postId);
}
package me.studybook.api.repo.post.mapper;

import me.studybook.api.repo.user.mapper.UserMapper;

import java.time.LocalDateTime;
import java.util.List;

public interface PostDetailMapper {
    Long getId();
    String getTitle();
    String getContent();
    LocalDateTime getUpdatedAt();
    UserMapper getUser();
    List<PostTagMapper> getPostTags();
}

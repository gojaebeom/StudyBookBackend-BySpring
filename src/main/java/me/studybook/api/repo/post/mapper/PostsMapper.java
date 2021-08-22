package me.studybook.api.repo.post.mapper;

import me.studybook.api.domain.PostTag;
import me.studybook.api.domain.User;
import me.studybook.api.repo.user.mapper.UserMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface PostsMapper {
    Long getId();
    String getTitle();
    Long getViews();
    LocalDateTime getUpdatedAt();
    UserMapper getUser();
    List<PostTagMapper> getPostTags();
}


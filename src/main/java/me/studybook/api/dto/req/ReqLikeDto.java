package me.studybook.api.dto.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import me.studybook.api.domain.Post;
import me.studybook.api.domain.PostLike;
import me.studybook.api.domain.User;
import me.studybook.api.repo.post.mapper.PostMapper;

@Getter
@ToString
@AllArgsConstructor
public class ReqLikeDto {
    private Long userId;
    private Long postId;

    public PostLike toPostLike(User user, Post post) {
        return PostLike.builder()
                .user(user)
                .post(post)
                .build();
    }
}

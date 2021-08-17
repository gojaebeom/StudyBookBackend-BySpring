package me.studybook.api.dto.res;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.studybook.api.domain.PostTag;
import me.studybook.api.repo.post.mapper.PostDetailMapper;
import me.studybook.api.repo.user.mapper.UserMapper;
import me.studybook.api.service.TimeToNaturalTime;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@ToString
@Getter
public class ResPostDetailDto {
    private Long id;
    private String title;
    private String content;
    private String publishedAt;
    private UserMapper user;
    private List<String> tags;

    @Builder
    public ResPostDetailDto(Long id, String title, String content, String publishedAt, UserMapper user, List<String> tags) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.publishedAt = publishedAt;
        this.user = user;
        this.tags = tags;
    }

    public static ResPostDetailDto of(PostDetailMapper postDetail, List<PostTag> postTags) {
        List<String> tags = new ArrayList<>();
        for(PostTag _tag: postTags){
            String tag = _tag.getTag().getName();
            tags.add(tag);
        }

        ResPostDetailDto postDetailDto = ResPostDetailDto.builder()
                .id(postDetail.getId())
                .title(postDetail.getTitle())
                .content(postDetail.getContent())
                .user(postDetail.getUser())
                .publishedAt(TimeToNaturalTime.formatTimeString(postDetail.getUpdatedAt()))
                .tags(tags)
                .build();

        return postDetailDto;
    }
}

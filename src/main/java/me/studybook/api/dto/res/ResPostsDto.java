package me.studybook.api.dto.res;

import lombok.*;
import me.studybook.api.domain.PostTag;
import me.studybook.api.repo.post.mapper.PostsMapper;
import me.studybook.api.repo.user.mapper.UserMapper;
import me.studybook.api.service.TimeToNaturalTime;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@ToString
public class ResPostsDto {

    private Long id;
    private String title;
    private String publishedAt;
    private UserMapper user;
    private List<String> tags;

    @Builder
    public ResPostsDto(Long id, String title, String publishedAt, UserMapper user, List<String> tags) {
        this.id = id;
        this.title = title;
        this.publishedAt = publishedAt;
        this.user = user;
        this.tags = tags;
    }

    public static List<ResPostsDto> of(List<PostsMapper> _posts, List<PostTag> postTags) {
        List<ResPostsDto> postsDtos = new ArrayList<>();

        for(PostsMapper post : _posts){
            List<String> tags = new ArrayList<>();
            for(PostTag postTag: postTags){
                if(post.getId() == postTag.getPost().getId()){
                    tags.add(postTag.getTag().getName());
                }
            }
            ResPostsDto resPostsDto = ResPostsDto.builder()
                    .id(post.getId())
                    .title(post.getTitle())
                    .publishedAt(TimeToNaturalTime.formatTimeString(post.getUpdatedAt()))
                    .tags(tags)
                    .user(post.getUser())
                    .build();
            postsDtos.add(resPostsDto);
        }

        return postsDtos;
    }
}

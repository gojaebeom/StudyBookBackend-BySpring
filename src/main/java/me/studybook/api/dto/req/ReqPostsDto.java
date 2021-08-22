package me.studybook.api.dto.req;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReqPostsDto {
    private Integer page = 1;
    private String sort = "resent";
    private String keyword = "";
}

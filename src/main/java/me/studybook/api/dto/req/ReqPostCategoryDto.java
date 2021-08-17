package me.studybook.api.dto.req;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReqPostCategoryDto {
    private String name;
    private Long userId;
}

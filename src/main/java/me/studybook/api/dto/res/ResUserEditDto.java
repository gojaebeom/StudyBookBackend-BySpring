package me.studybook.api.dto.res;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ResUserEditDto {
    private String act;

    @Builder
    public ResUserEditDto(String act) {
        this.act = act;
    }
}

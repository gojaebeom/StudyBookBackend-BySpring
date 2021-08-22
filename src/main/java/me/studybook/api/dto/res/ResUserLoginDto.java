package me.studybook.api.dto.res;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class ResUserLoginDto {
    private String accessToken;
    private String refreshToken;

    @Builder
    public ResUserLoginDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}

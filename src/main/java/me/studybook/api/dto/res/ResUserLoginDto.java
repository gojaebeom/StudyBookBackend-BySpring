package me.studybook.api.dto.res;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class ResUserLoginDto {
    private String accessToken;
    private String refreshToken;
    private Long userId;
    private String profile;

    @Builder
    public ResUserLoginDto(String accessToken, String refreshToken, Long userId, String profile) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.userId = userId;
        this.profile = profile;
    }
}

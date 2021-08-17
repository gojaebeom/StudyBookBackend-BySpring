package me.studybook.api.dto.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@ToString
@Getter
@Setter
@AllArgsConstructor
public class ReqUserEditDto {
    private Long id;
    private String nickname;
    private MultipartFile profile;
}

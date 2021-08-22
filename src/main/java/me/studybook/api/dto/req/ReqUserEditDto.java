package me.studybook.api.dto.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@ToString
@Getter
@Setter
public class ReqUserEditDto {
    private Long id;
    private String nickname;
    private String info;
    private MultipartFile profile;
    private Boolean initImg;
}

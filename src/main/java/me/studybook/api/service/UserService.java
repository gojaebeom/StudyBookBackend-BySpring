package me.studybook.api.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.studybook.api.domain.User;
import me.studybook.api.dto.req.ReqUserEditDto;
import me.studybook.api.dto.res.ResUserDetailDto;
import me.studybook.api.dto.res.ResUserEditDto;
import me.studybook.api.repo.post.PostRepo;
import me.studybook.api.repo.user.UserRepo;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;
import java.io.File;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class UserService {

    private UserRepo userRepo;
    private PostRepo postRepo;

    public ResUserDetailDto show(Long id) throws Exception {
        return ResUserDetailDto.of(userRepo.findUserById(id));
    }

    public ResUserEditDto edit(ReqUserEditDto userEditDto) throws Exception{
        User user = userRepo.findUserById(userEditDto.getId());

        user.setNickname(userEditDto.getNickname());
        user.setInfo(userEditDto.getInfo());

        String act = "";
        // 원본, 리사이징 이미지 파일이름 2개를 받음
        if(userEditDto.getProfile() != null){
            System.out.println("이미지 존재");

            File profile = new File("C:\\tmp\\"+user.getProfile());
            File profilePreview = new File("C:\\tmp\\"+user.getProfilePreview());

            ImageFileService.deletes(profile, profilePreview);

            String[] imageNames = ImageFileService.upload(userEditDto.getProfile());
            user.setProfile(imageNames[0]);
            user.setProfilePreview(imageNames[1]);

            act = TokenService.createAct(user.getId(), user.getProfilePreview());
        }

        // 이미지 초기화
        if(userEditDto.getInitImg()){
            File profile = new File("C:\\tmp\\"+user.getProfile());
            File profilePreview = new File("C:\\tmp\\"+user.getProfilePreview());

            ImageFileService.deletes(profile, profilePreview);
            user.setProfile("");
            user.setProfilePreview("");
        }

        return ResUserEditDto.builder()
                .act(act)
                .build();
    }

    public void destroy(Long userId) throws Exception {
        userRepo.deleteById(userId);
    }
}

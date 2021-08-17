package me.studybook.api.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.studybook.api.dto.res.ResUserDetailDto;
import me.studybook.api.repo.post.PostRepo;
import me.studybook.api.repo.user.UserRepo;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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

    public void destroy(Long userId) throws Exception {
        userRepo.deleteById(userId);
    }
}

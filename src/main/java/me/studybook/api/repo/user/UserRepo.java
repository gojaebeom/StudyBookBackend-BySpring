package me.studybook.api.repo.user;

import me.studybook.api.domain.User;
import me.studybook.api.repo.user.mapper.UserMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo extends JpaRepository<User, Long> {

    UserMapper findUserByUsername(String username) throws Exception;

    UserMapper findFirstByOrderByIdDesc() throws Exception;

    Long countByUsername(String username) throws Exception;

    User findUserById(Long userId) throws Exception;
}
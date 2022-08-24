package org.example.domain;

import org.example.domain.model.UserEntity;
import org.example.infrastructure.model.po.User;
import org.example.rest.model.user.UserRequest;
import org.example.rest.model.user.UserVo;
import org.springframework.data.domain.Page;

public interface UserService {

    User registerUser(UserEntity user);

    User login(String account, String password);

    void deleteUser(Long id);

    Page<UserVo> findUserList(UserRequest request);

}

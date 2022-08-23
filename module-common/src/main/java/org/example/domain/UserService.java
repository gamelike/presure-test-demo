package org.example.domain;

import org.example.domain.model.UserEntity;
import org.example.infrastructure.model.po.User;

import java.util.List;

public interface UserService {

    User registerUser(UserEntity user);

    User login(String account, String password);

    void deleteUser(Long id);

    List<User> findUserByName(String username);

    List<User> findAll();
}

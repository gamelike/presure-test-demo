package org.example.infrastructure.repository;

import org.example.infrastructure.model.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByAccountEquals(String account);

    List<User> findUsersByUsernameContaining(String username);

}
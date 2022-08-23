package org.example.domain.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.UserService;
import org.example.domain.model.UserEntity;
import org.example.infrastructure.model.po.User;
import org.example.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public User registerUser(UserEntity user) {
        user.valid();
        return userRepository.save(new User().setAccount(user.account())
                .setUsername(user.username())
                .setFirstName(user.firstName())
                .setLastName(user.lastName())
                .setPassword(DigestUtils.md5DigestAsHex(user.password().getBytes(StandardCharsets.UTF_8)))
                .setEmail(user.email())
        );
    }

    @Override
    public User login(String account, String password) {
        User user = userRepository.findUserByAccountEquals(account);
        user.validPassword(password);
        return user;
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findUserByName(String username) {
        return userRepository.findUsersByUsernameContaining(username);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}

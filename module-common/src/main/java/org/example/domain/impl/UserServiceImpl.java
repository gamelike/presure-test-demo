package org.example.domain.impl;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.UserService;
import org.example.domain.model.UserEntity;
import org.example.infrastructure.model.po.User;
import org.example.infrastructure.repository.UserRepository;
import org.example.rest.model.user.UserRequest;
import org.example.rest.model.user.UserVo;
import org.hibernate.query.criteria.internal.predicate.InPredicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

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
    @Transactional
    public Page<UserVo> findUserList(UserRequest request) {
        Page<User> users = userRepository.findAll((Specification<User>) (root, query, builder) -> {
            List<Predicate> predicates = Lists.newArrayList();
            if (request.username() != null) {
                predicates.add(builder.like(root.get("username"), "%" + request.username() + "%"));
            }
            return query.where(predicates.toArray(new Predicate[0])).getRestriction();
        }, PageRequest.of(request.offset(), request.limit()));
        List<UserVo> collect = users.stream().map(User::to).toList();
        return new PageImpl<>(collect, users.getPageable(), users.getTotalElements());
    }

}

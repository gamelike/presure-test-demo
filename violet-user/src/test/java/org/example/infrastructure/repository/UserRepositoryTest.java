package org.example.infrastructure.repository;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.example.base.annotation.JpaTest;
import org.example.infrastructure.model.po.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.JoinType;

/**
 * @author violet
 */
@Slf4j
@JpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void specification_executor_join_return_sql() {
        // NOTE:
        Page<User> userList = userRepository.findAll((Specification<User>) (root, query, criteriaBuilder) -> {
            root.join("postList", JoinType.LEFT);
            return criteriaBuilder.conjunction();
        }, Pageable.unpaged());

        Assertions.assertThat(userList.getTotalElements())
                .isEqualTo(0L);
    }

}

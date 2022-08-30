package org.example.infrasturctrue;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.example.base.annotation.JpaTest;
import org.example.infrastructure.model.po.User;
import org.example.infrastructure.repository.UserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.JoinType;

/**
 * @author violet
 */
@JpaTest
@Slf4j
public class UserRepositoryTest {

    @Autowired
    UserRepository repository;

    @Test
    public void specification_executor_join_return_sql() {
        // NOTE:
        Page<User> userList = repository.findAll((Specification<User>) (root, query, criteriaBuilder) -> {
            root.join("postList", JoinType.LEFT);
//            Path<Integer> id = postList.get("userId");
//            postList.on(criteriaBuilder.equal(root.get("id"), id.get("userId")));
//            List<Predicate> list = Lists.newArrayList();
//            list.add(postList.getOn());
            return criteriaBuilder.conjunction();
        }, Pageable.unpaged());

        Assertions.assertThat(userList.getTotalElements())
                .isEqualTo(0L);
    }

}

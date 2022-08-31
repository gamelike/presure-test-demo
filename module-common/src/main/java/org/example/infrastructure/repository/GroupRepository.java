package org.example.infrastructure.repository;

import org.example.infrastructure.model.po.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author violet
 * @since 2022/8/31
 */
public interface GroupRepository extends JpaRepository<UserGroup, Long>, JpaSpecificationExecutor<UserGroup> {
}

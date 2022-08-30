package org.example.infrastructure.model.po;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

/**
 * @author violet
 */
@Entity
@Table(name = "user_group")
@Data
@Accessors(chain = true)
public class UserGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 32)
    private String name;

    @OneToMany(targetEntity = User.class, mappedBy = "groupId")
    private List<User> userList;

}

package org.example.infrastructure.model.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.example.exception.ArgsException;
import org.example.rest.model.user.UserVo;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.util.DigestUtils;

import javax.persistence.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "community_user")
@Accessors(chain = true)
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 32)
    private String firstName;

    @Column(nullable = false, length = 32)
    private String lastName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "GMT+8")
    @Column(nullable = false, length = 32)
    @CreationTimestamp
    private LocalDateTime createTime;

    @Column(nullable = false, length = 64)
    private String email;

    @Column(nullable = false, length = 32)
    private String account;

    @Column(length = 32)
    private String username;

    @Column(nullable = false, length = 32)
    private String password;

    @OneToMany(targetEntity = Post.class, mappedBy = "userId")
    private List<Post> postList;

    public User setPassword(String password) {
        this.password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        return this;
    }

    public String getPassword() {
        return this.password;
    }

    public void validPassword(String password) {
        if (!Objects.equals(this.password, DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8)))) {
            throw new ArgsException("用户名或密码错误");
        }
    }

    public UserVo to() {
        return new UserVo(this.username, this.account, this.firstName, this.lastName, this.email, this.createTime);
    }

}

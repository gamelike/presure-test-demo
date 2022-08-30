package org.example.infrastructure.model.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "community_post")
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false, length = 32)
    private Long userId;

    @Column(nullable = false, length = 128)
    private String subject;

    @Column(name = "content", nullable = true, length = 1024)
    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "GMT+8")
    @Column(name = "createTime", nullable = false, length = 32)
    @CreationTimestamp
    private LocalDateTime createTime;

}

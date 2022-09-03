package org.example.infrastructure.model.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.example.utils.custom.deserialize.CustomDateDeserialize;
import org.example.utils.custom.serialize.CustomDataSerializer;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

    @Column(name = "content", nullable = true,length = 1024)
    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "GMT+8")
    @Column(name = "create_time", nullable = false, length = 32)
    @JsonSerialize(using = CustomDataSerializer.class)
    @JsonDeserialize(using = CustomDateDeserialize.class)
    @CreationTimestamp
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "GMT+8")
    @Column(name = "update_time", nullable = false, length = 32)
    @JsonSerialize(using = CustomDataSerializer.class)
    @JsonDeserialize(using = CustomDateDeserialize.class)
    @UpdateTimestamp
    private LocalDateTime updateTime;

}

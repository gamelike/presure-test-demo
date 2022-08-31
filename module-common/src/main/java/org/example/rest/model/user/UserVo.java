package org.example.rest.model.user;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.annotation.Nullable;
import java.time.LocalDateTime;

/**
 * @author violet
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record UserVo(String username,
                     String account,
                     String firstName,
                     String lastName,
                     String email,
                     @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime createTime,
                     @Nullable String token) {
}

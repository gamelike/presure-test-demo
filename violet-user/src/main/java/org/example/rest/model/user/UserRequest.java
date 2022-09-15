package org.example.rest.model.user;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * @author violet
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record UserRequest(
        String username,
        int offset,
        int limit
) {
}

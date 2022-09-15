package org.example.rest.model.user;

import org.example.infrastructure.model.po.UserGroup;

/**
 * user auth record.
 *
 * @author violet
 */
public record AuthRecord(String token, UserGroup group) {
}

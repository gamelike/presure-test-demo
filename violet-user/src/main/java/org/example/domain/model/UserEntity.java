package org.example.domain.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.example.exception.ArgsException;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record UserEntity(String firstName, String lastName, String account, String password, String username,String email, String groupId) {

    public void valid() {
        if (firstName == null && lastName == null && account == null && password == null) {
            throw new ArgsException("用户注册参数校验不成功!");
        }
        if (groupId == null) {
            throw new ArgsException("用户组不能为空!");
        }
    }

}

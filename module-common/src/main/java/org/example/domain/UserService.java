package org.example.domain;

import org.example.constant.AssetType;
import org.example.domain.model.UserEntity;
import org.example.rest.model.user.UserRequest;
import org.example.rest.model.user.UserVo;
import org.springframework.data.domain.Page;

public interface UserService {

    /**
     * register user.
     *
     * @param user {@link UserEntity}
     * @return {@link UserVo}
     */
    UserVo registerUser(UserEntity user);

    UserVo login(String account, String password, AssetType assetType);

    boolean logout(long userId, AssetType assetType);

    boolean activeUser(long userId);

    void deleteUser(long id);

    Page<UserVo> findUserList(UserRequest request);

}

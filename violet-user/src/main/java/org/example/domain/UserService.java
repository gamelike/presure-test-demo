package org.example.domain;

import org.example.constant.AssetType;
import org.example.domain.model.UserEntity;
import org.example.rest.model.user.UserRequest;
import org.example.rest.model.user.UserVo;
import org.springframework.data.domain.Page;

/**
 * user service.
 *
 * @author violet
 * @since 2022-8-30
 */
public interface UserService {

    /**
     * register user.
     *
     * @param user {@link UserEntity}
     * @return {@link UserVo}
     */
    UserVo registerUser(UserEntity user);

    /**
     * user login.
     *
     * @param account   user account.
     * @param password  user password.
     * @param assetType login asset.
     * @return {@link UserVo}
     */
    UserVo login(String account, String password, AssetType assetType);

    /**
     * user logout.
     *
     * @param userId    user id.
     * @param assetType login asset.
     * @return ?success.
     */
    boolean logout(long userId, AssetType assetType);

    /**
     * user active.
     *
     * @param userId user id.
     * @return ?success.
     */
    boolean activeUser(long userId);

    /**
     * delete user.
     *
     * @param id user id.
     */
    void deleteUser(long id);

    /**
     * find page user by conditional {@link UserRequest}
     *
     * @param request query condition.
     * @return {@link Page<UserVo>}
     */
    Page<UserVo> findUserList(UserRequest request);

}

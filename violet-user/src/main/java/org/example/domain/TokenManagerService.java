package org.example.domain;

import org.example.constant.AssetType;
import org.example.domain.model.TokenEntity;
import org.example.infrastructure.model.po.User;

/**
 * manager token of the user.
 *
 * @author violet
 */
public interface TokenManagerService {


    /**
     * find token by account.
     *
     * @param account user id.
     * @param assetType {@link AssetType}
     * @return {@link TokenEntity}
     */
    TokenEntity findToken(String account, AssetType assetType);

    /**
     * generate token to db.
     *
     * @param account user id.
     * @param assetType {@link AssetType}
     * @param user {@link User}
     * @return {@link TokenEntity}.
     */
    TokenEntity createToken(String account, AssetType assetType, User user);

    /**
     * renewal token.
     *
     * @param account user id.
     * @param assetType {@link AssetType}
     * @return {@link TokenEntity}
     */
    boolean subscribeToken(String account, AssetType assetType);

    /**
     * delete token.
     *
     * @param account   user id.
     * @param assetType {@link AssetType}
     * @return delete success token.
     */
    boolean deleteToken(String account, AssetType assetType);

}

package org.example.domain;

import org.example.domain.model.TokenEntity;

/**
 * manager token of the user.
 *
 * @author violet
 */
public interface TokenManagerService {


    /**
     * find token by userId.
     *
     * @param userId user id.
     * @return {@link TokenEntity}
     */
    TokenEntity findToken(String userId);

    /**
     * generate token to db.
     *
     * @param userId user id.
     * @return {@link TokenEntity}.
     */
    TokenEntity createToken(String userId);

    /**
     * renewal token.
     *
     * @param userId user id.
     * @param token token.
     * @return {@link TokenEntity}
     */
    TokenEntity subscribeToken(String userId,String token);

    /**
     * delete token.
     *
     * @param userId user id.
     * @param token token.
     */
    void deleteToken(String userId, String token);

}

package org.example.utils;

import org.example.constant.AssetType;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * @author violet
 * @since 2022/8/31
 */
public class RedisKeyUtil {

    private static final String PREFIX = "community";

    private static final String SPLIT = ":";

    public static String getTokenKey(String account, AssetType assetType) {
        return DigestUtils.md5DigestAsHex((account + assetType).getBytes(StandardCharsets.UTF_8));
    }

}

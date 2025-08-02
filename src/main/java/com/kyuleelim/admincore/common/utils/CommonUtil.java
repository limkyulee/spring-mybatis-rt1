package com.kyuleelim.admincore.common.utils;

import java.util.UUID;

/**
 * @author limkyulee
 * @version 1.0, 8/1/25
 * @see 전역적 사용 Util
 */
public class CommonUtil {


    /**
     * @Method generateUuid
     * @Description UUID 생성
     * @param prefix
     * @return UUID
     */
    public static String generateUuid(String prefix) {
        UUID uuid = UUID.randomUUID();
        return prefix + uuid.toString();
    }
}

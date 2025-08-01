package com.kyuleelim.admincore.common.utils;

/**
 * @author limkyulee
 * @version 1.0, 8/1/25
 * @see Personal Info Masked Util
 */
public class MaskedUtil {

    /**
     * @Method maskPersonalInfo
     * @Description 개인정보 마스킹 처리
     * @param data
     * @param type 마스킹 타입 (name, phone, email, customer, device, model, ip 등)
     * @return 마스킹 된 문자열
     */
    public static String maskPersonalInfo(String type, String data) {
        if (data == null || data.trim().isEmpty()) {
            return data;
        }

        return switch (type.toLowerCase()) {
            case "name" -> maskName(data);
            case "phone" -> maskPhone(data);
            case "email" -> maskEmail(data);
            case "default" -> maskDefault(data);
            case "ip" -> maskIpAddress(data);
            default -> data;
        };
    }

    /**
     * @Method maskName
     * @Description 이름 마스킹 처리
     * 한국어: 성 뒤 이름 첫자리 마스킹 (예: 김철수 → 김*수)
     * 영문: 앞뒤 각 2자리 제외하고 마스킹 (예: John Smith → Jo***th)
     * @param name
     * @return 마스킹 된 이름
     */
    public static String maskName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return name;
        }

        name = name.trim();
        if (name.length() <= 1) {
            return name;
        }

        // 한글인지 영문인지 판단
        boolean isKorean = isKoreanName(name);

        if (isKorean) {
            // 한국어 이름 처리: 성 뒤 이름 첫자리 마스킹
            if (name.length() == 2) {
                return name.charAt(0) + "*";
            } else if (name.length() >= 3) {
                // 첫 글자(성) + 마스킹 + 마지막 글자
                return name.charAt(0) + "*" + name.substring(2);
            }
        } else {
            // 영문 이름 처리: 앞뒤 각 2자리 제외하고 마스킹
            if (name.length() <= 4) {
                return name.charAt(0) + "*".repeat(name.length() - 2) + name.charAt(name.length() - 1);
            } else {
                // 앞 2자리 + 마스킹 + 뒤 2자리
                int maskLength = name.length() - 4;
                return name.substring(0, 2) + "*".repeat(maskLength) + name.substring(name.length() - 2);
            }
        }

        return name;
    }

    /**
     * @Method isKoreanName
     * @Description 한국어 이름인지 판단
     * @param name
     * @return 한국어 이름 여부
     */
    private static boolean isKoreanName(String name) {
        for (char c : name.toCharArray()) {
            if (c >= 0xAC00 && c <= 0xD7A3) {
                return true;
            }
        }
        return false;
    }

    /**
     * @Method maskPhone
     * @Description 전화번호 마스킹 처리
     * @param phone
     * @return 마스킹된 전화번호
     */
    public static String maskPhone(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            return phone;
        }

        phone = phone.replaceAll("[^0-9]", "");
        if (phone.length() < 8) {
            return phone;
        }

        if (phone.length() == 10) {
            return phone.substring(0, 3) + "***" + phone.substring(6);
        } else if (phone.length() == 11) {
            return phone.substring(0, 3) + "****" + phone.substring(7);
        } else {
            int maskLength = phone.length() - 6;
            return phone.substring(0, 3) + "*".repeat(maskLength) + phone.substring(phone.length() - 3);
        }
    }

    /**
     * @Method maskEmail
     * @Description 이메일 마스킹 처리
     * @param email
     * @return 마스킹 된 이메일
     */
    public static String maskEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return email;
        }

        int atIndex = email.indexOf("@");
        if (atIndex <= 0) {
            return email;
        }

        String localPart = email.substring(0, atIndex);
        String domainPart = email.substring(atIndex);

        if (localPart.length() <= 2) {
            // 2글자 이히: 첫글자 + * + 도메인
            return localPart.charAt(0) + "*" + domainPart;
        } else if (localPart.length() == 3) {
            // 3글자: 처음 2글자 + * + 도메인
            return localPart.substring(0, 2) + "*" + domainPart;
        } else {
            int maskLength = localPart.length() - 3;
            return localPart.substring(0, 3) + "*".repeat(maskLength) + domainPart;
        }
    }

    /**
     * @Method maskDefault
     * @Description 기본 마스킹 처리 (뒤 4자리 외 마스킹)
     * @param value
     * @return 마스킹 된 값
     */
    public static String maskDefault(String value) {
        if (value == null || value.trim().isEmpty()) {
            return value;
        }

        value = value.trim();
        if (value.length() <= 4) {
            return value;
        }

        int maskLength = value.length() - 4;
        return "*".repeat(maskLength) + value.substring(value.length() - 4);
    }

    /**
     * @Method maskIpAddress
     * @Description IP 주소 마스킹 처리 (두 번째 옥텟 마스킹)
     * @param ipAddress
     * @return 마스킹 된 IP 주소
     */
    public static String maskIpAddress(String ipAddress) {
        if (ipAddress == null || ipAddress.trim().isEmpty()) {
            return ipAddress;
        }

        String[] octets = ipAddress.trim().split("\\.");
        if (octets.length != 4) {
            return ipAddress;
        }

        return octets[0] + "." + octets[1] + ".***." + octets[3];
    }
}

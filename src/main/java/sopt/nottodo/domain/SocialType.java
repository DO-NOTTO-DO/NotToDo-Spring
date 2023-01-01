package sopt.nottodo.domain;

import nottodo.util.exception.CustomException;
import nottodo.util.response.ResponseCode;

import java.util.Arrays;

public enum SocialType {
    KAKAO, APPLE, TEST;

    public static SocialType from(String social) {
        return Arrays.stream(SocialType.values())
                .filter(socialType -> socialType.toString().equals(social))
                .findFirst()
                .orElseThrow(() -> new CustomException(ResponseCode.INVALID_SOCIAL_TYPE));
    }
}

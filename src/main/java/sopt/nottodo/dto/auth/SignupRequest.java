package sopt.nottodo.dto.auth;

import lombok.Data;
import sopt.nottodo.domain.FcmToken;
import sopt.nottodo.domain.SocialType;

@Data
public class SignupRequest {

    private final String email;
    private final SocialType socialType;
    private final String SocialId;
    private final String name;
    private final String image;
    private final FcmToken fcmToken;
}

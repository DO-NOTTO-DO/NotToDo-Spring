package sopt.nottodo.auth.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt.nottodo.auth.service.AuthService;
import sopt.nottodo.auth.service.SocialLoginService;
import sopt.nottodo.auth.support.JwtTokenProvider;
import sopt.nottodo.auth.domain.SocialType;
import sopt.nottodo.auth.dto.LoginRequest;
import sopt.nottodo.auth.dto.LoginResponse;
import sopt.nottodo.auth.repository.UserRepository;
import sopt.nottodo.auth.service.social.AppleLoginService;
import sopt.nottodo.auth.service.social.KakaoLoginService;
import sopt.nottodo.auth.service.social.TestLoginService;

import javax.annotation.PostConstruct;
import java.util.EnumMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final Map<SocialType, SocialLoginService> socialLogins = new EnumMap<>(SocialType.class);
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional
    public LoginResponse socialLogin(String social, LoginRequest request) {
        SocialType socialType = SocialType.from(social);
        SocialLoginService socialLoginService = socialLogins.get(socialType);
        return socialLoginService.socialLogin(request);
    }

    @PostConstruct
    private void init() {
        socialLogins.put(SocialType.APPLE, new AppleLoginService(userRepository, jwtTokenProvider));
        socialLogins.put(SocialType.KAKAO, new KakaoLoginService(userRepository, jwtTokenProvider));
        socialLogins.put(SocialType.TEST, new TestLoginService(userRepository, jwtTokenProvider));
    }
}

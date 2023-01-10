package sopt.nottodo.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt.nottodo.auth.JwtTokenProvider;
import sopt.nottodo.domain.SocialType;
import sopt.nottodo.dto.auth.LoginRequest;
import sopt.nottodo.dto.auth.LoginResponse;
import sopt.nottodo.repository.UserRepository;
import sopt.nottodo.service.AuthService;
import sopt.nottodo.service.SocialLoginService;
import sopt.nottodo.service.impl.social.AppleLoginService;
import sopt.nottodo.service.impl.social.KakaoLoginService;
import sopt.nottodo.service.impl.social.TestLoginService;

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

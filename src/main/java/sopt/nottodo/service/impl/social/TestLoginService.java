package sopt.nottodo.service.impl.social;

import lombok.RequiredArgsConstructor;
import sopt.nottodo.auth.JwtTokenProvider;
import sopt.nottodo.domain.FcmToken;
import sopt.nottodo.domain.SocialType;
import sopt.nottodo.domain.User;
import sopt.nottodo.dto.auth.LoginRequest;
import sopt.nottodo.dto.auth.LoginResponse;
import sopt.nottodo.dto.auth.SignupRequest;
import sopt.nottodo.repository.UserRepository;
import sopt.nottodo.service.SocialLoginService;
import sopt.nottodo.util.exception.CustomException;
import sopt.nottodo.util.response.ResponseCode;

@RequiredArgsConstructor
public class TestLoginService implements SocialLoginService {

    private static final String TEST_USER = "테스트 유저";
    private static final String TEST_IMAGE = "https://cdn-icons-png.flaticon.com/512/847/847969.png";

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public LoginResponse socialLogin(LoginRequest request) {
        validateEmail(request);
        String email = request.getEmail();
        FcmToken fcmToken = new FcmToken(request.getFcmToken());
        SignupRequest signupRequest = new SignupRequest(email, SocialType.TEST, TEST_USER, TEST_USER, TEST_IMAGE, fcmToken);
        String accessToken = issueAccessToken(signupRequest);
        return new LoginResponse(accessToken);
    }

    private void validateEmail(LoginRequest request) {
        if (request.getEmail() == null) {
            throw new CustomException(ResponseCode.NO_VALUE_REQUIRED);
        }
    }

    private String issueAccessToken(SignupRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseGet(() -> signup(request));
        return jwtTokenProvider.createToken(user.getEmail());
    }

    private User signup(SignupRequest request) {
        User user = new User(request);
        return userRepository.save(user);
    }
}

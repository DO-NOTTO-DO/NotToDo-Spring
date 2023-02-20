package sopt.nottodo.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import sopt.nottodo.auth.support.JwtTokenProvider;
import sopt.nottodo.auth.controller.AuthController;
import sopt.nottodo.auth.domain.FcmToken;
import sopt.nottodo.auth.domain.SocialType;
import sopt.nottodo.auth.domain.User;
import sopt.nottodo.auth.dto.SignupRequest;
import sopt.nottodo.auth.service.AuthService;
import sopt.nottodo.auth.service.UserService;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@WebMvcTest({AuthController.class})
public class ControllerTest {

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @MockBean
    protected JwtTokenProvider jwtTokenProvider;
    @MockBean
    protected AuthService authService;
    @MockBean
    protected UserService userService;

    protected final String ACCESS_TOKEN = "access_token";
    protected final String INVALID_CODE = "aaaaaaaa";

    @BeforeEach
    void createMemberAndGetAccessToken() {
        FcmToken fcmToken = new FcmToken("FCM 토큰");
        SignupRequest signupRequest = new SignupRequest("test@example.com", SocialType.TEST, "1a", "테스트 코드", "테스트 이미지", fcmToken);
        Optional<User> user = Optional.of(new User(signupRequest));
        when(jwtTokenProvider.getAccessTokenPayload(anyString())).thenReturn("1");
        when(userService.findById(anyLong())).thenReturn(user.get());
    }
}

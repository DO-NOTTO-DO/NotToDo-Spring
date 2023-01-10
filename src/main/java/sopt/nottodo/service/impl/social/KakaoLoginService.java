package sopt.nottodo.service.impl.social;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RequiredArgsConstructor
public class KakaoLoginService implements SocialLoginService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Value("${social.kakao-url}")
    private String kakaoUrl;

    @Override
    public LoginResponse socialLogin(LoginRequest request) {
        StringBuilder kakaoInfo = getKakaoInfo(request.getSocialToken());
        JsonElement element = JsonParser.parseString(kakaoInfo.toString());
        validateHasEmail(element);
        String accessToken = getAccessToken(element, request.getFcmToken());
        return new LoginResponse(accessToken);
    }

    private StringBuilder getKakaoInfo(String socialToken) {
        try {
            URL url = new URL(kakaoUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Authorization", "Bearer " + socialToken);
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            StringBuilder result = new StringBuilder();
            while ((line = br.readLine()) != null) {
                result.append(line);
            }
            br.close();
            return result;
        } catch (IOException e) {
            throw new CustomException(ResponseCode.FAILED_VALIDATE_KAKAO_LOGIN);
        }
    }

    private void validateHasEmail(JsonElement element) {
        boolean hasEmail = element
                .getAsJsonObject().get("kakao_account")
                .getAsJsonObject().get("has_email")
                .getAsBoolean();
        if(!hasEmail){
            throw new CustomException(ResponseCode.DISAGREE_KAKAO_EMAIL);
        }
    }

    private String getAccessToken(JsonElement element, String fcmToken) {
        String email = element
                .getAsJsonObject().get("kakao_account")
                .getAsJsonObject().get("email")
                .getAsString();
        String name = element
                .getAsJsonObject().get("properties")
                .getAsJsonObject().get("nickname")
                .getAsString();
        String image = element
                .getAsJsonObject().get("properties")
                .getAsJsonObject().get("profile_image")
                .getAsString();
        String socialId = element
                .getAsJsonObject().get("id")
                .getAsString();
        FcmToken token = new FcmToken(fcmToken);

        return issueAccessToken(new SignupRequest(email, SocialType.KAKAO, socialId, name, image, token));
    }

    private String issueAccessToken(SignupRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElse(signup(request));
        return jwtTokenProvider.createToken(user.getEmail());
    }

    private User signup(SignupRequest request) {
        return userRepository.save(new User(request));
    }
}

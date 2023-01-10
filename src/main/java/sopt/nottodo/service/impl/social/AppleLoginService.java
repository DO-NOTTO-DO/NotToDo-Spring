package sopt.nottodo.service.impl.social;

import com.google.gson.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ObjectUtils;
import sopt.nottodo.auth.JwtTokenProvider;
import sopt.nottodo.domain.FcmToken;
import sopt.nottodo.domain.SocialType;
import sopt.nottodo.domain.User;
import sopt.nottodo.dto.auth.LoginRequest;
import sopt.nottodo.dto.auth.LoginResponse;
import sopt.nottodo.dto.auth.SignupRequest;
import sopt.nottodo.repository.FcmTokenRepository;
import sopt.nottodo.repository.UserRepository;
import sopt.nottodo.service.SocialLoginService;
import sopt.nottodo.util.exception.CustomException;
import sopt.nottodo.util.response.ResponseCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.Objects;

@RequiredArgsConstructor
public class AppleLoginService implements SocialLoginService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Value("${social.apple-url}")
    private String appleUrl;

    @Override
    public LoginResponse socialLogin(LoginRequest request) {
        validateName(request);
        String socialToken = request.getSocialToken();
        JsonObject jsonObject = getJsonObject(getAppleInfo(), socialToken);
        PublicKey publicKey = decodePublicKey(jsonObject);
        String accessToken = getAccessToken(publicKey, request);
        return new LoginResponse(accessToken);
    }

    private void validateName(LoginRequest request) {
        if (request.getName() == null) {
            throw new CustomException(ResponseCode.NO_VALUE_REQUIRED);
        }
    }

    private StringBuilder getAppleInfo() {
        try {
            StringBuilder result = new StringBuilder();
            URL url = new URL(appleUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            while ((line = br.readLine()) != null) {
                result.append(line);
            }
            br.close();
            return result;
        } catch (IOException e) {
            throw new CustomException(ResponseCode.FAILED_VALIDATE_APPLE_LOGIN);
        }
    }

    private JsonObject getJsonObject(StringBuilder result, String socialToken) {
        JsonObject keys = (JsonObject) JsonParser.parseString(result.toString());
        JsonArray keyArray = (JsonArray) keys.get("keys");
        String[] decodeArray = socialToken.split("\\.");
        String header = new String(Base64.getDecoder().decode(decodeArray[0]));
        JsonElement kid = ((JsonObject) JsonParser.parseString(header)).get("kid");
        JsonElement alg = ((JsonObject) JsonParser.parseString(header)).get("alg");
        JsonObject avaliableObject = null;
        for (int i = 0; i < keyArray.size(); i++) {
            JsonObject appleObject = (JsonObject) keyArray.get(i);
            JsonElement appleKid = appleObject.get("kid");
            JsonElement appleAlg = appleObject.get("alg");
            if (Objects.equals(appleKid, kid) && Objects.equals(appleAlg, alg)) {
                avaliableObject = appleObject;
                break;
            }
        }
        if (ObjectUtils.isEmpty(avaliableObject)) {
            throw new CustomException(ResponseCode.BAD_REQUEST);
        }
        return avaliableObject;
    }

    private String getAccessToken(PublicKey publicKey, LoginRequest request) {
        String socialToken = request.getSocialToken();
        Claims userInfo = Jwts.parserBuilder().setSigningKey(publicKey).build().parseClaimsJws(socialToken).getBody();
        JsonObject userInfoObject = (JsonObject) JsonParser.parseString(new Gson().toJson(userInfo));
        String email =  userInfoObject.get("email").getAsString();
        String name = request.getName();
        // TODO: 이미지 어케 할건지 생각해야함
        String image = "https://cdn-icons-png.flaticon.com/512/847/847969.png";
        FcmToken fcmToken = new FcmToken(request.getFcmToken());

        // TODO: 찐 애플 토큰 넣어야함
        String socialId = "임시 토큰";
        return issueAccessToken(new SignupRequest(email, SocialType.APPLE, socialId, name, image, fcmToken));
    }

    private PublicKey decodePublicKey(JsonObject object) {
        String nStr = object.get("n").toString();
        String eStr = object.get("e").toString();
        byte[] nBytes = Base64.getUrlDecoder().decode(nStr.substring(1, nStr.length() - 1));
        byte[] eBytes = Base64.getUrlDecoder().decode(eStr.substring(1, eStr.length() - 1));
        BigInteger n = new BigInteger(1, nBytes);
        BigInteger e = new BigInteger(1, eBytes);
        return getPublicKey(n, e);
    }

    private PublicKey getPublicKey(BigInteger n, BigInteger e) {
        try {
            RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(n, e);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(publicKeySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            throw new CustomException(ResponseCode.INVALID_APPLE_KEY);
        }
    }

    private String issueAccessToken(SignupRequest request) {
        User user = userRepository.findBySocialId(request.getSocialId())
                .orElse(signup(request));
        return jwtTokenProvider.createToken(user.getEmail());
    }

    private User signup(SignupRequest request) {
        return userRepository.save(new User(request));
    }
}

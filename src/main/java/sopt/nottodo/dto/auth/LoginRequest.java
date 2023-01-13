package sopt.nottodo.dto.auth;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {

    @NotBlank
    private final String socialToken;
    @NotBlank
    private final String fcmToken;
    // only for apple
    private String name;
    // only for test
    @Email
    private String email;

    public LoginRequest(String socialToken, String fcmToken, @Nullable String name, @Nullable String email) {
        this.socialToken = socialToken;
        this.fcmToken = fcmToken;
        this.name = name;
        this.email = email;
    }
}

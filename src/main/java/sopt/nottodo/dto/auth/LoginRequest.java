package sopt.nottodo.dto.auth;

import lombok.Data;

@Data
public class LoginRequest {

    private final String socialToken;
    private final String fcmToken;
    // only for apple
    private String name;
    // only for test
    private String email;
}

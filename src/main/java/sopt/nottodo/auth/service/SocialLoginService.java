package sopt.nottodo.auth.service;

import sopt.nottodo.auth.dto.LoginRequest;
import sopt.nottodo.auth.dto.LoginResponse;

@FunctionalInterface
public interface SocialLoginService {

    LoginResponse socialLogin(LoginRequest request);
}

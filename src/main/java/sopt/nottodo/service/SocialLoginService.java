package sopt.nottodo.service;

import sopt.nottodo.dto.auth.LoginRequest;
import sopt.nottodo.dto.auth.LoginResponse;

@FunctionalInterface
public interface SocialLoginService {

    LoginResponse socialLogin(LoginRequest request);
}

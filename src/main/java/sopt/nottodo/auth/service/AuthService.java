package sopt.nottodo.auth.service;

import org.springframework.transaction.annotation.Transactional;
import sopt.nottodo.auth.dto.LoginRequest;
import sopt.nottodo.auth.dto.LoginResponse;

public interface AuthService {

    @Transactional
    LoginResponse socialLogin(String social, LoginRequest request);
}

package sopt.nottodo.service;

import org.springframework.transaction.annotation.Transactional;
import sopt.nottodo.dto.auth.LoginRequest;
import sopt.nottodo.dto.auth.LoginResponse;

public interface AuthService {

    @Transactional
    LoginResponse socialLogin(String social, LoginRequest request);
}

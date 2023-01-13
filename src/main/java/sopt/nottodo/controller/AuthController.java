package sopt.nottodo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sopt.nottodo.dto.auth.LoginRequest;
import sopt.nottodo.service.AuthService;
import sopt.nottodo.util.response.ResponseCode;
import sopt.nottodo.util.response.ResponseDataMessage;
import sopt.nottodo.util.response.ResponseMessage;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login/{social}")
    public ResponseEntity<ResponseMessage> socialLogin(@PathVariable String social, @Valid @RequestBody LoginRequest request) {
        return ResponseDataMessage.toResponseEntity(
                ResponseCode.LOGIN_SUCCESS,
                authService.socialLogin(social, request)
        );
    }
}

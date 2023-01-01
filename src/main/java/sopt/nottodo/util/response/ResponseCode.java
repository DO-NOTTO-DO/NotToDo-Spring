package sopt.nottodo.util.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ResponseCode {

    // common
    BAD_REQUEST(HttpStatus.BAD_REQUEST, false, "잘못된 문법으로 인하여 서버가 요청을 이해할 수 없습니다."),
    NO_VALUE_REQUIRED(HttpStatus.BAD_REQUEST, false, "필요한 값이 없습니다."),
    INVALID_BODY_TYPE(HttpStatus.BAD_REQUEST, false, "json 형식으로 메시지를 보내주세요."),
    INVALID_BODY_OR_HEADER(HttpStatus.BAD_REQUEST, false, "잘못된 바디나 헤더입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, false, "서버 내부 오류입니다."),

    // auth
    LOGIN_SUCCESS(HttpStatus.CREATED, true, "로그인 성공"),
    LOGIN_FAILED(HttpStatus.BAD_REQUEST, false, "로그인에 실패하였습니다."),
    DISAGREE_KAKAO_EMAIL(HttpStatus.BAD_REQUEST, false, "카카오 이메일 항목에 동의하지 않았습니다."),
    FAILED_VALIDATE_KAKAO_LOGIN(HttpStatus.BAD_REQUEST, false, "카카오 로그인 오류입니다."),
    FAILED_VALIDATE_APPLE_LOGIN(HttpStatus.BAD_REQUEST, false, "애플 로그인 오류입니다."),
    INVALID_APPLE_KEY(HttpStatus.INTERNAL_SERVER_ERROR, false, "애플 로그인 알고리즘 혹은 키 오류입니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, false, "해당 계정이 존재하지 않습니다."),
    INVALID_SOCIAL_TYPE(HttpStatus.BAD_REQUEST, false, "유효하지 않는 소셜 타입입니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, false, "유효하지 않은 토큰입니다."),

    // user
    GET_USER_INFO_SUCCESS(HttpStatus.OK, true, "사용자 정보 조회 성공"),
    DELETE_USER_SUCCESS(HttpStatus.OK, true, "탈퇴 성공");

    private final HttpStatus httpStatus;
    private final Boolean success;
    private final String message;
}

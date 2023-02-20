package sopt.nottodo.common.util.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import sopt.nottodo.common.util.response.ResponseCode;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {
    private final ResponseCode responseCode;
}

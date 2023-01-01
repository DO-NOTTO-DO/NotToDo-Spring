package sopt.nottodo.util.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import nottodo.util.response.ResponseCode;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {
    private final ResponseCode responseCode;
}

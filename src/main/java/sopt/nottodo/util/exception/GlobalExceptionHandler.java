package sopt.nottodo.util.exception;

import lombok.extern.slf4j.Slf4j;
import nottodo.util.response.ResponseCode;
import nottodo.util.response.ResponseMessage;
import nottodo.util.response.ResponseNonDataMessage;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    protected ResponseEntity<ResponseMessage> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        CustomException customException = new CustomException(ResponseCode.INVALID_BODY_TYPE);
        log.debug("Bad request exception occurred: {}", e.getMessage(), e);
        return ResponseNonDataMessage.toResponseEntity(customException.getResponseCode());
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    protected ResponseEntity<ResponseMessage> handleConstraintViolationException(ConstraintViolationException e) {
        CustomException customException = new CustomException(ResponseCode.INVALID_BODY_OR_HEADER);
        log.debug("Bad request exception occurred: {}", e.getMessage(), e);
        return ResponseNonDataMessage.toResponseEntity(customException.getResponseCode());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    protected ResponseEntity<ResponseMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        CustomException customException = new CustomException(ResponseCode.NO_VALUE_REQUIRED);
        log.debug("Bad request exception occurred: {}", e.getMessage(), e);
        return ResponseNonDataMessage.toResponseEntity(customException.getResponseCode());
    }

    @ExceptionHandler(value = CustomException.class)
    protected ResponseEntity<ResponseMessage> handleCustomException(CustomException e) {
        log.debug("Bad request exception occurred: {}", e.getMessage(), e);
        return ResponseNonDataMessage.toResponseEntity(e.getResponseCode());
    }

    @ExceptionHandler({Exception.class, RuntimeException.class})
    protected ResponseEntity<ResponseMessage> handleAllException(Exception e) {
        log.error("Unexpected exception occurred: {}", e.getMessage(), e);
        return ResponseNonDataMessage.toResponseEntity(ResponseCode.INTERNAL_SERVER_ERROR);
    }
}

package sopt.nottodo.common.util.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
@SuperBuilder
public class ResponseNonDataMessage extends ResponseMessage {

    public static ResponseEntity<ResponseMessage> toResponseEntity(ResponseCode responseCode) {
        return ResponseEntity
                .status(responseCode.getHttpStatus())
                .body(ResponseNonDataMessage.builder()
                        .status(responseCode.getHttpStatus().value())
                        .success(responseCode.getSuccess())
                        .message(responseCode.getMessage())
                        .build()
                );
    }
}

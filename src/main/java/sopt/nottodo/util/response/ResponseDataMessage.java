package sopt.nottodo.util.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
@SuperBuilder
public class ResponseDataMessage extends ResponseMessage {

    private Object data;

    public static ResponseEntity<ResponseMessage> toResponseEntity(ResponseCode responseCode, Object data) {
        return ResponseEntity
                .status(responseCode.getHttpStatus())
                .body(ResponseDataMessage.builder()
                        .status(responseCode.getHttpStatus().value())
                        .success(responseCode.getSuccess())
                        .message(responseCode.getMessage())
                        .data(data)
                        .build()
                );
    }
}

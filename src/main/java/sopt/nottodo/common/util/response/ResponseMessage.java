package sopt.nottodo.common.util.response;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public abstract class ResponseMessage {
    private final int status;
    private final boolean success;
    private final String message;
}

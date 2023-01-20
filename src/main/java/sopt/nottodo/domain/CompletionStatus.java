package sopt.nottodo.domain;

import sopt.nottodo.util.exception.CustomException;
import sopt.nottodo.util.response.ResponseCode;

import java.util.Arrays;

public enum CompletionStatus {
    FINISH(1), AMBIGUOUS(0.5f), NOTYET(0);

    private final float point;

    CompletionStatus(float point) {
        this.point = point;
    }

    public float getPoint() {
        return point;
    }

    public static CompletionStatus from(String status) {
        return Arrays.stream(CompletionStatus.values())
                .filter(completionStatus -> completionStatus.toString().equals(status))
                .findFirst()
                .orElseThrow(() -> new CustomException(ResponseCode.INVALID_COMPLETION_STATUS));
    }
}

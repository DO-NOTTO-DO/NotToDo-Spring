package sopt.nottodo.dto.mission;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import sopt.nottodo.domain.CompletionStatus;

@Getter
@RequiredArgsConstructor
public class MissionCompletionStatusDto {

    private final Long id;
    private final CompletionStatus completionStatus;
}

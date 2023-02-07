package sopt.nottodo.dto.mission;

import lombok.Getter;
import sopt.nottodo.domain.CompletionStatus;
import sopt.nottodo.domain.Mission;

@Getter
public class MissionCompletionStatusDto {

    private final Long id;
    private final CompletionStatus completionStatus;

    public MissionCompletionStatusDto(Mission mission) {
        id = mission.getId();
        completionStatus = mission.getCompletionStatus();
    }
}

package sopt.nottodo.mission.dto;

import lombok.Getter;
import sopt.nottodo.mission.domain.CompletionStatus;

@Getter
public class DailyMissionStatusDto {

    private final String actionDate;
    private final CompletionStatus completionStatus;

    public DailyMissionStatusDto(String actionDate, CompletionStatus completionStatus) {
        this.actionDate = actionDate;
        this.completionStatus = completionStatus;
    }
}

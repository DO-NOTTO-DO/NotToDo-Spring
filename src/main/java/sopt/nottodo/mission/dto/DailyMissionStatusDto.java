package sopt.nottodo.mission.dto;

import sopt.nottodo.mission.domain.CompletionStatus;

public class DailyMissionStatusDto {

    private final String actionDate;
    private final CompletionStatus completionStatus;

    public DailyMissionStatusDto(String actionDate, CompletionStatus completionStatus) {
        this.actionDate = actionDate;
        this.completionStatus = completionStatus;
    }
}

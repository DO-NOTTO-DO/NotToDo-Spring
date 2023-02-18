package sopt.nottodo.dto.mission;

import lombok.Getter;
import sopt.nottodo.domain.CompletionStatus;
import sopt.nottodo.domain.DailyMission;

@Getter
public class DailyMissionDto {

    private final Long id;
    private final String title;
    private final CompletionStatus completionStatus;
    private final String situation;

    public DailyMissionDto(DailyMission dailyMission) {
        this.id = dailyMission.getMission().getId();
        this.title = dailyMission.getMission().getTitle();
        this.completionStatus = dailyMission.getCompletionStatus();
        this.situation = dailyMission.getMission().getSituation().getName();
    }
}
